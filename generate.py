import collections
import fnmatch
import re
import textwrap
import os
import contextlib
from textwrap import indent

# Attempting to use relative paths would mess up the file tree depending on where the script runs.
# We will instead locate java sources relative to the running script.

rootdir = os.path.dirname(os.path.realpath(__file__))
sources = rootdir + '/src/main/java/com/machinezoo/noexception'
tests = rootdir + '/src/test/java/com/machinezoo/noexception'

# We will first create our knowledge base of information about functional interfaces.
# This is directly derived from JDK documentation.
# Since the functional interfaces are repetitive, we will help ourselves generously with regexes.
# These functions however still appear to be simple tables from the point of view of their callers.
# Every function takes the name of the functional interface and returns some data about it.
# Raw data matching JDK documentation is complemented with functions that return derived or slightly transformed data.

def functional_types():
    yield 'Runnable'
    yield 'Supplier'
    primitives = ['Int', 'Long', 'Double']
    for type in primitives + ['Boolean']:
        yield type + 'Supplier'
    yield 'Consumer'
    for type in primitives:
        yield type + 'Consumer'
    yield 'BiConsumer'
    for type in primitives:
        yield 'Obj' + type + 'Consumer'
    yield 'Predicate'
    for type in primitives:
        yield type + 'Predicate'
    yield 'BiPredicate'
    yield 'Function'
    for type in primitives:
        yield 'To' + type + 'Function'
        yield type + 'Function'
        for type2 in primitives:
            if type != type2:
                yield type + 'To' + type2 + 'Function'
    yield 'UnaryOperator'
    for type in primitives:
        yield type + 'UnaryOperator'
    yield 'BiFunction'
    for type in primitives:
        yield 'To' + type + 'BiFunction'
    yield 'BinaryOperator'
    for type in primitives:
        yield type + 'BinaryOperator'
    yield 'Comparator'
    yield 'CloseableScope'

def nonvoid_functional_types():
    for functional in functional_types():
        if not void_functional(functional):
            yield functional

def parameterless_functional_types():
    for functional in functional_types():
        if '' == declared_params(functional):
            yield functional

def is_primitive(type):
    # Only primitive types we care about are listed here.
    return type in ['int', 'long', 'double', 'boolean']

# Example:
#    'double' == return_type('DoubleSupplier')
#    'T' == return_type('Supplier')
def return_type(functional):
    cases = [
        ('void', 'Runnable|.*Consumer|CloseableScope'),
        ('T', 'Supplier|UnaryOperator|BinaryOperator'),
        ('int', 'IntSupplier|.*ToInt.*|Int.*Operator|Comparator'),
        ('long', 'LongSupplier|.*ToLong.*|Long.*Operator'),
        ('double', 'DoubleSupplier|.*ToDouble.*|Double.*Operator'),
        ('boolean', 'BooleanSupplier|.*Predicate'),
        ('R', '.*Function'),
    ]
    return next(c[0] for c in cases if re.fullmatch(c[1], functional))

def void_functional(functional):
    return 'void' == return_type(functional)

# Example:
#    'Object' == erased_return('Function')
#    'int' == erased_return('IntSupplier')
def erased_return(functional):
    return re.sub('T|U|R', 'Object', return_type(functional))

# Method names in functional interfaces as declared in JDK, excluding the As* suffix.
# Since we will be sometimes promoting return type to an object, e.g. int to OptionalInt,
# we have to correspondingly change method name, e.g. getAsInt to plain 'get'.
# This is why we have to define method verb separately from any As* suffix.

# Example: 'apply' == method_verb('ToIntFunction')
def method_verb(functional):
    cases = [
        ('run', 'Runnable'),
        ('get', '.*Supplier'),
        ('accept', '.*Consumer'),
        ('apply', '.*Function|.*Operator'),
        ('test', '.*Predicate'),
        ('compare', 'Comparator'),
        ('close', 'CloseableScope'),
    ]
    return next(c[0] for c in cases if re.fullmatch(c[1], functional))

# Example: 'applyAsInt' == as_method('ToIntFunction')
def as_method(functional):
    verb = method_verb(functional)
    if verb in ['test', 'compare']:
        return verb
    returns = return_type(functional)
    if is_primitive(returns):
        return verb + 'As' + returns.capitalize()
    return verb

# Example: 'T t, int value' == declared_params('ObjIntConsumer')
def declared_params(functional):
    cases = [
        ('', 'Runnable|.*Supplier|CloseableScope'),
        ('T t, U u', 'BiConsumer|BiPredicate|.*BiFunction'),
        ('T t', 'Consumer|Predicate|Function'),
        ('T value', 'To.*Function'),
        ('PRIMITIVE value', '(Int|Long|Double)(?:Consumer|Predicate|.*Function)'),
        ('T t, PRIMITIVE value', 'Obj(Int|Long|Double)Consumer'),
        ('T operand', 'UnaryOperator'),
        ('PRIMITIVE operand', '(Int|Long|Double)UnaryOperator'),
        ('T left, T right', 'BinaryOperator|Comparator'),
        ('PRIMITIVE left, PRIMITIVE right', '(Int|Long|Double)BinaryOperator'),
    ]
    for case in cases:
        match = re.fullmatch(case[1], functional)
        if match:
            params = case[0]
            if match.groups():
                params = params.replace('PRIMITIVE', match.group(1).lower())
            return params
    raise ValueError(functional)

# Example: 't, value' == passed_params('ObjIntConsumer')
def passed_params(functional):
    return re.sub('[a-zA-Z]+ ([a-z]+)', r'\1', declared_params(functional))

# Example: 'Object, int' == erased_params('ObjIntConsumer')
def erased_params(functional):
    return re.sub(r'\b(?:T|U|R)\b', 'Object', re.sub('([a-zA-Z]+) [a-z]+', r'\1', declared_params(functional)))

# Example: ['t', 'value'] == param_names('ObjIntConsumer')
def param_names(functional):
    return filter(None, re.split(', *', passed_params(functional)))

# Lambda parameter list built from initials of parameter names. It is used in javadoc and tests.
# Example: '(t, v)' == lambda_params('ObjIntConsumer')
def lambda_params(functional):
    initials = [n[0] for n in param_names(functional)]
    joined = ", ".join(initials)
    if len(initials) == 1:
        return joined
    return '(' + joined + ')'

# Example: '<T, R>' == type_parameter_section('Function')
def type_parameter_section(functional):
    cases = [
        ('<T, R>', 'Function'),
        ('<T, U, R>', 'BiFunction'),
        ('<T, U>', 'BiConsumer|BiPredicate|To.*BiFunction'),
        ('<T>', 'Consumer|Predicate|Supplier|To.*Function|Obj.*Consumer|UnaryOperator|BinaryOperator|Comparator'),
        ('', '.*To.*Function'),
        ('<R>', '.*Function'),
        ('', 'Runnable|.*Supplier|.*Consumer|.*Predicate|.*Operator|CloseableScope'),
    ]
    return next(c[0] for c in cases if re.fullmatch(c[1], functional))

# Example: 'Function<T, R>' == parameterized_type('Function')
def parameterized_type(functional):
    return functional + type_parameter_section(functional)

# Example: ['T', 'R'] == type_params('Function')
def type_params(functional):
    return filter(None, re.split(r'\W+', type_parameter_section(functional)))

def extends_type(functional):
    if functional == 'UnaryOperator':
        return 'Function<T, T>'
    if functional == 'BinaryOperator':
        return 'BiFunction<T, T, T>'
    return None

# Example: 'ThrowingFunction' == throwing_variant('Function')
def throwing_variant(functional):
    if functional == 'CloseableScope':
        return 'AutoCloseable'
    return 'Throwing' + functional

# Example: 'ThrowingSupplier<T>' == parameterized_throwing_variant('Supplier')
def parameterized_throwing_variant(functional):
    return throwing_variant(functional) + type_parameter_section(functional)

# We will now define noexception conventions that are a matter of choice.

def executable_functional_types():
    for functional in parameterless_functional_types():
        if functional not in ['CloseableScope']:
            yield functional

# Example:
#    'runnable' == from_method('Runnable')
#    'fromIntFunction' == from_method('IntFunction')
def from_method(functional):
    if functional == 'CloseableScope':
        return 'closeable'
    elif functional[1:] == functional[1:].lower():
        # Functional interfaces with one-word names have short method names, e.g. 'runnable()'. This makes the API nicer.
        return functional.lower()
    else:
        # Everything else follows from* pattern, which is universal if lengthy.
        return 'from' + functional

# Example: 'function' == short_name('DoubleToIntFunction')
def short_name(functional):
    return re.fullmatch('.*(Function|Consumer|Predicate|Supplier|Operator|Runnable|Comparator|Closeable).*', functional).group(1).lower()

# Example: 'Function<T, OptionalInt>' == optional_base('ToIntFunction')
# This is not strictly a matter of choice. The best possible base class was picked for all Optional* classes.
def optional_base(functional):
    cases = [
        ('Supplier', 'Supplier<Optional<T>>'),
        ('(.*)Supplier', r'Supplier<Optional\1>'),
        ('Function', 'Function<T, Optional<R>>'),
        ('BiFunction', 'BiFunction<T, U, Optional<R>>'),
        ('To(.*)BiFunction', r'BiFunction<T, U, Optional\1>'),
        ('To(.*)Function', r'Function<T, Optional\1>'),
        ('(.*)To(.*)Function', r'\1Function<Optional\2>'),
        ('(.*)Function', r'\1Function<Optional<R>>'),
        ('UnaryOperator', 'Function<T, Optional<T>>'),
        ('(.*)UnaryOperator', r'\1Function<Optional\1>'),
        ('BinaryOperator', 'BiFunction<T, T, Optional<T>>'),
    ]
    matches = ((re.fullmatch(c[0], functional), c[1]) for c in cases)
    return next((m[0].expand(m[1]) for m in matches if m[0]), None)

# Example: '<String, String>' == test_type_parameter_section('Function')
def test_type_parameter_section(functional):
    return re.sub('T|U|R', 'String', type_parameter_section(functional))

# Example: 'Function<String, String>' == test_type_parameter_section('Function')
def test_parameterized_type(functional):
    return functional + test_type_parameter_section(functional)

# Example: 'ThrowingSupplier<String>' == test_parameterized_throwing_variant('Supplier')
def test_parameterized_throwing_variant(functional):
    return throwing_variant(functional) + test_type_parameter_section(functional)

def test_return_type(functional):
    return re.sub('T|R', 'String', return_type(functional))

def test_output(functional):
    table = {
        'void': '',
        'int': '2',
        'long': '2L',
        'double': '2.0',
        'boolean': 'true',
    }
    return table.get(return_type(functional), '"value"')

# Example:
#    'Optional.of("value")' == optional_test_output('Function')
#    'OptionalInt.of(2)' == optional_test_output('IntSupplier')
def optional_test_output(functional):
    return optional_of(return_type(functional), test_output(functional))

def test_fallback(functional):
    table = {
        'void': '',
        'int': '3',
        'long': '3L',
        'double': '3.0',
        'boolean': 'false',
    }
    return table.get(return_type(functional), '"default"')

def test_input(functional):
    cases = [
        ('', ''),
        ('int', '1'),
        ('long', '1L'),
        ('double', '1.0'),
        ('int, int', '11, 12'),
        ('long, long', '11L, 12L'),
        ('double, double', '1.1, 1.2'),
        ('T, int', '"input", 1'),
        ('T, long', '"input", 1L'),
        ('T, double', '"input", 1.0'),
        ('T', '"input"'),
        ('T, U|T, T', '"input1", "input2"'),
    ]
    parameters = re.sub('([a-zA-Z]+) [a-z]+', r'\1', declared_params(functional))
    return next(c[1] for c in cases if re.fullmatch(c[0], parameters))

def test_precision(functional):
    return ', 0.1' if 'double' == return_type(functional) else ''

def file_header(subpackage=''):
    return '''\
        // Part of NoException: https://noexception.machinezoo.com
        // Generated code. Edit generate.py instead.
        package com.machinezoo.noexception''' + subpackage + ''';

    '''

def usually_a_lambda(functional):
    return ', usually a lambda' if functional not in ['CloseableScope'] else ''

# Numerous utility functions used in various parts of the code are defined here.

# Example: 'Supplier' == raw_type('Supplier<String>')
def raw_type(type):
    return re.sub('<.*>', '', type)

# Example:
#    'Supplier<String>' == supplier_of('String')
#    'IntSupplier' == supplier_of('int')
def supplier_of(type):
    if is_primitive(type):
        return type.capitalize() + 'Supplier'
    return 'Supplier<' + type + '>'

# Example:
#    'Optional<String>' == optional_type('String')
#    'OptionalInt' == optional_type('int')
def optional_type(type):
    # Special case for void, so that we can use this on void return without extra conditions.
    if type == 'void':
        return 'void'
    if is_primitive(type):
        return 'Optional' + type.capitalize()
    return 'Optional<' + type + '>'

# Example:
#    'Optional.of("hello")' == optional_of('String', '"hello"')
#    'OptionalInt.of(123)' == optional_of('int', '123')
def optional_of(type, value):
    # Special case for void like in optional_of().
    if type == 'void':
        return value
    return raw_type(optional_type(type)) + '.of(' + value + ')'

# Example:
#    'Optional.ofNullable("hello")' == optional_of_nullable('String', '"hello"')
#    'OptionalInt.of(123)' == optional_of_nullable('int', '123')
def optional_of_nullable(type, value):
    # Special case for void like in optional_of().
    if type == 'void':
        return value
    if is_primitive(type):
        return optional_type(type) + '.of(' + value + ')'
    return 'Optional.ofNullable(' + value + ')'

# Example:
#    'Optional' == raw_optional_return('Function')
#    'OptionalInt' == raw_optional_return('IntSupplier')
# This is the most common use of optional types.
def raw_optional_return(functional):
    return raw_type(optional_type(return_type(functional)))

def space_left(text):
    return ' ' + text if text else ''

def return_if_needed(type):
    return 'return ' if type != 'void' else ''

def test_unchecked(functional):
    return '@SuppressWarnings("unchecked") ' if type_parameter_section(functional) else ''

# We can now proceed to generate the individual files.
# We will first define some helper functions that simplify our code generators.

def output(text, indent=0):
    text = textwrap.dedent(text)
    if text[-1:] != '\n':
        text += '\n'
    if re.fullmatch('(?:[*].*\n)+', text):
        text = textwrap.indent(text, ' ')
    for i in range(0, 5):
        text = re.sub('^(\t*) {4}', r'\1\t', text, flags=re.MULTILINE)
    text = textwrap.indent(text, indent * '\t')
    print(text, end='')

def redirect(path, generator):
    with open(path, 'w') as file:
        with contextlib.redirect_stdout(file):
            generator()

# Here we define generators for various types of files.
# These generators output to stdout. They will be redirected later.

def throwing_source(fn):
    output(file_header('.throwing'))
    if fn == 'Comparator':
        output('import java.util.*;')
    if fn not in ['Runnable', 'Comparator']:
        output('import java.util.function.*;')
    from_method_ref = 'CheckedExceptionHandler#' + from_method(fn) + '(Throwing' + fn + ')'
    output('''\
        import com.machinezoo.noexception.*;
        
        /**
         * Variation of {@link ''' + fn + '''} that allows throwing checked exceptions.
         * {@code Throwing''' + fn + '''} is usually implemented by a lambda
         * and passed to {@link ''' + from_method_ref + '''}.
         * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
         * 
    ''')
    for type in type_params(fn):
        output('''\
             * @param <''' + type + '''>
             *            see {@link ''' + fn + '''}
        ''')
    extends = extends_type(fn)
    throwing_extends = ' extends Throwing' + extends if extends else ''
    output('''\
         * @see CheckedExceptionHandler#''' + from_method(fn) + '(Throwing' + fn + ''')
         * @see ''' + fn + '''
         */
        @FunctionalInterface
        public interface Throwing''' + parameterized_type(fn) + throwing_extends + ''' {
    ''')
    if not extends:
        original_method_ref = fn + '#' + as_method(fn) + '(' + erased_params(fn) + ')'
        output('''\
            /**
             * Variation of {@link ''' + original_method_ref  + '''} that allows throwing checked exceptions.
             * 
        ''', indent=1)
        for name in param_names(fn):
            output('''\
                 * @param ''' + name + '''
                 *            see {@link ''' + original_method_ref  + '''}
            ''', indent=1)
        if not void_functional(fn):
            output(' * @return see {@link ' + original_method_ref + '}', indent=1)
        output('''\
             * @throws Throwable
             *             if unable to complete
             * @see ''' + from_method_ref + '''
             * @see ''' + original_method_ref  + '''
             */
            ''' + return_type(fn) + ' ' + as_method(fn) + '(' + declared_params(fn) + ''') throws Throwable;
        ''', indent=1)
    output('}')

def default_source(fn):
    output(file_header('.optional'))
    if fn == 'Comparator':
        output('import java.util.*;')
    else:
        output('import java.util.function.*;')
    output('''\
        
        final class Default''' + parameterized_type(fn) + ' implements ' + parameterized_type(fn) + ''' {
            private final Optional''' + parameterized_type(fn) + ''' inner;
            private final ''' + return_type(fn) + ''' result;
            public Default''' + fn + '(Optional' + parameterized_type(fn) + ' inner, ' + return_type(fn) + ''' result) {
                this.inner = inner;
                this.result = result;
            }
            @Override
            public ''' + return_type(fn) + ' ' + as_method(fn) + '(' + declared_params(fn) + ''') {
                return inner.''' + method_verb(fn) + '(' + passed_params(fn) + ''').orElse(result);
            }
        }
    ''')

def fallback_source(fn):
    output(file_header('.optional'))
    if fn == 'Comparator':
        output('import java.util.*;')
    output('''\
        import java.util.function.*;
        
        final class Fallback''' + parameterized_type(fn) + ' implements ''' + parameterized_type(fn) + ''' {
            private final Optional''' + parameterized_type(fn) + ''' inner;
            private final ''' + supplier_of(return_type(fn)) + ''' source;
            public Fallback''' + fn + '(Optional' + parameterized_type(fn) + ' inner, ' + supplier_of(return_type(fn)) + ''' source) {
                this.inner = inner;
                this.source = source;
            }
            @Override
            public ''' + return_type(fn) + ' ' + as_method(fn) + '(' + declared_params(fn) + ''') {
                return inner.''' + method_verb(fn) + '(' + passed_params(fn) + ''').orElseGet(source);
            }
        }
    ''')

def optional_source(fn):
    output(file_header('.optional'))
    if return_type(fn) != 'boolean':
        output('import java.util.*;')
    output('''\
        import java.util.function.*;
        import com.machinezoo.noexception.*;
        
        /**
         * Variation of {@link ''' + fn + '} that returns {@link ' + raw_optional_return(fn) + '''} instead of the raw value.
         * {@code Optional''' + fn + '} is typically obtained from {@link ExceptionHandler#' + from_method(fn) + '(' + fn + ''')},
         * in which case its return value is empty when the underlying {@link ''' + fn + '''} throws an exception.
         * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
         * 
    ''')
    for type in type_params(fn):
        output('''\
             * @param <''' + type + '''>
             *            see {@link ''' + fn + '''}
        ''')
    original_method_ref = fn + '#' + as_method(fn) + '(' + erased_params(fn) + ')'
    handler_method_ref = 'ExceptionHandler#' + from_method(fn) + '(' + fn + ')'
    output('''\
         * @see ''' + handler_method_ref + '''
         * @see ''' + fn + '''
         */
        @FunctionalInterface
        public interface Optional''' + parameterized_type(fn) + (' extends ' + optional_base(fn) if optional_base(fn) else '') + ''' {
            /**
             * Variation of {@link ''' + original_method_ref + '} that returns {@link ' + raw_optional_return(fn) + '''}.
             * If this {@code Optional''' + fn + '} is obtained from {@link ' + handler_method_ref + '''},
             * the {@link ''' + raw_optional_return(fn) + '} will be empty only if the underlying {@link ' + fn + '''} throws.
             * Otherwise the returned {@link ''' + raw_optional_return(fn) + '} just wraps the return value of underlying {@link ' + fn
                + '}' + (' (possibly {@code null})' if not is_primitive(return_type(fn)) else '') + '''.
             * 
    ''')
    for name in param_names(fn):
        output('''\
             * @param ''' + name + '''
             *            see {@link ''' + original_method_ref  + '''}
        ''', indent=1)
    raw_supplier = raw_type(supplier_of(return_type(fn)))
    output('''\
             * @return {@link ''' + raw_optional_return(fn) + '} typically wrapping return value of {@link ' + original_method_ref + '''},
             *         or an empty {@link ''' + raw_optional_return(fn) + '''} (typically signifying an exception)
             * @see ''' + handler_method_ref + '''
             * @see ''' + original_method_ref + '''
             */
            ''')
    if optional_base(fn):
        output('@Override', indent=1)
    output('''\
            ''' + optional_type(return_type(fn)) + ' ' + method_verb(fn) + '(' + declared_params(fn) + ''');
            /**
             * Converts this {@code Optional''' + fn + '} to plain {@link ' + fn + '''} using default value.
             * The returned {@link ''' + fn + '} will unwrap present value from the {@link ' + raw_optional_return(fn) + '''} if possible,
             * or return {@code result} if the {@link ''' + raw_optional_return(fn) + '''} is empty.
             * 
             * @param result
             *            default value to return instead of an empty {@link ''' + raw_optional_return(fn) + '''}
             * @return plain {@link ''' + fn + '} that either unwraps {@link ' + raw_optional_return(fn) + '''} or returns default value
             * @see #orElseGet(''' + raw_supplier + ''')
             * @see ''' + raw_optional_return(fn) + '#orElse(' + erased_return(fn) + ''')
             */
            default ''' + parameterized_type(fn) + ' orElse(' + return_type(fn) + ''' result) {
                return new Default''' + parameterized_type(fn) + '''(this, result);
            }
            /**
             * Converts this {@code Optional''' + fn + '} to plain {@link ' + fn + '} using fallback {@link ' + raw_supplier + '''}.
             * The returned {@link ''' + fn + '} will unwrap present value from the {@link ' + raw_optional_return(fn) + '''} if possible,
             * or fall back to calling {@code source} if the {@link ''' + raw_optional_return(fn) + '''} is empty.
             * 
             * @param source
             *            {@link ''' + raw_supplier + '} to query for fallback value when {@link ' + raw_optional_return(fn) + '''} is empty
             * @return plain {@link ''' + fn + '} that either unwraps {@link ' + raw_optional_return(fn) + '''} or falls back to {@code source}
             * @see #orElse(''' + erased_return(fn) + ''')
             * @see ''' + raw_optional_return(fn) + '#orElseGet(' + raw_supplier + ''')
             */
            default ''' + parameterized_type(fn) + ' orElseGet(' + supplier_of(return_type(fn)) + ''' source) {
                return new Fallback''' + parameterized_type(fn) + '''(this, source);
            }
        }
    ''')

def handler_source():
    output(file_header())
    output('''\
        import java.util.*;
        import java.util.function.*;
        import com.machinezoo.noexception.optional.*;
        
        /**
         * Represents exception handling policy.
         * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
         * Method {@link #handle(Throwable)} defines the exception handling policy when implemented in derived class.
         * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
         * <p>
         * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda).orElse(fallback)}
         * <p>
         * All wrapping methods surround the functional interface with a try-catch block.
         * If the functional interface throws, the exception is caught and passed to {@link #handle(Throwable)},
         * which applies exception handling policy (log, silence, ignore, custom).
         * {@link NullPointerException} from {@code null} functional interface is caught too.
         * Unless {@link #handle(Throwable)} requests a rethrow, void functional interfaces complete normally
         * while non-void functional interfaces return empty {@link Optional}.
         * <p>
         * Wrapping methods for all standard functional interfaces are provided.
         * Simple interfaces have short method names like {@link #runnable(Runnable)} or {@link #supplier(Supplier)}.
         * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(UnaryOperator)}.
         * Parameterless functional interfaces can be called directly by methods {@link #run(Runnable)}, {@link #get(Supplier)},
         * and the various {@code getAsX} variants.
         * <p>
         * All non-void wrappers conform to some {@code OptionalX} functional interface, for example {@link OptionalSupplier},
         * that is identical to its non-optional variant from JDK except it returns {@link Optional} instead of raw value.
         * This {@link Optional} is empty in case of exception.
         * Callers can use {@link Optional#orElse(Object)} and {@link Optional#orElseGet(Supplier)} and their
         * equivalents on {@code OptionalX} interfaces to provide fallback values.
         * 
         * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
         * @see #handle(Throwable)
         * @see Exceptions
         * @see OptionalSupplier
         * @see Optional
         * @see ExceptionFilter
         * @see CheckedExceptionHandler
         */
        public abstract class ExceptionHandler {
            /**
             * Handles exception in a generic way. This method must be defined in a derived class.
             * Several implementations are provided by methods on {@link Exceptions} class.
             * All other methods of the {@code ExceptionHandler} call this method, but it can be also called directly.
             * <p>
             * This method represents reusable catch block that handles all exceptions in the same way.
             * When invoked, it must somehow handle the provided exception, for example by logging it.
             * <p>
             * This method does not have to handle all exceptions.
             * It can indicate through return value whether it has accepted or rejected the exception.
             * When an exception is rejected, caller of this method is expected to rethrow the exception.
             * All other methods of this class fulfill this requirement.
             * 
             * @param exception
             *            the exception to handle
             * @return {@code true} when exception is handled, {@code false} if the exception should be rethrown
             * @throws NullPointerException
             *             if {@code exception} is {@code null}
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public abstract boolean handle(Throwable exception);
            /**
             * Initialize new {@code ExceptionHandler}.
             */
            protected ExceptionHandler() {
            }
            /**
             * Adds a pass-through modifier to this exception handler.
             * If this exception handler performs an action (like logging) and then stops exception propagation,
             * this method will return {@link ExceptionFilter} that performs the same action but additionally rethrows the exception.
             * <p>
             * Reusable exception handlers can be defined once as {@code ExceptionHandler} instances
             * and then transformed into {@link ExceptionFilter} by this method when needed.
             * <p>
             * If method {@link #handle(Throwable)} throws, the returned {@link ExceptionFilter} will pass through that exception.
             * It only rethrows the original exception if {@link #handle(Throwable)} returns normally (regardless of return value).
             * <p>
             * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda)}
             *
             * @return pass-through modification of this exception handler
             */
            public ExceptionFilter passing() {
                return new PassingFilter(this);
            }
    ''')
    for fn in functional_types():
        output('''\
            /**
             * Wraps {@link ''' + fn + '''} in a try-catch block.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws, the exception is caught and passed to {@link #handle(Throwable)},
             * which applies exception handling policy (log, silence, ignore, custom).
             * {@link NullPointerException} from {@code null} {@code ''' + short_name(fn) + '''} is caught too.
        ''', indent=1)
        if void_functional(fn):
            output(' * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.', indent=1)
        else:
            output(' * Wrapper then returns empty {@link ' + raw_optional_return(fn) + '} unless {@link #handle(Throwable)} requests a rethrow.', indent=1)
        wrapper_interface = ('' if void_functional(fn) else 'Optional') + parameterized_type(fn)
        def typical_usage():
            if fn == 'CloseableScope':
                return 'try (var scope = Exceptions.log().closeable(openSomething()))'
            orelse = '.orElse(fallback)' if 'void' != return_type(fn) else ''
            return 'Exceptions.log().' + from_method(fn) + '(' + lambda_params(fn) + ' -> my_throwing_lambda)' + orelse
        output('''\
             * <p>
             * Typical usage: {@code ''' + typical_usage() + '''}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + fn + '''} to wrap''' + usually_a_lambda(fn) + '''
             * @return wrapper that runs {@code ''' + short_name(fn) + '''} in a try-catch block
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + wrapper_interface + ' '
                + from_method(fn) + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                return new Catching''' + parameterized_type(fn) + '(' + short_name(fn) + ''');
            }
            private final class Catching''' + parameterized_type(fn) + ' implements ' + wrapper_interface + ''' {
                private final ''' + parameterized_type(fn) + ' ' + short_name(fn) + ''';
                Catching''' + fn + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                    this.''' + short_name(fn) + ' = ' + short_name(fn) + ''';
                }
                @Override
                public ''' + optional_type(return_type(fn)) + ' ' + method_verb(fn) + '(' + declared_params(fn) + ''') {
                    try {
                        ''' + return_if_needed(return_type(fn))
                            + optional_of_nullable(return_type(fn), short_name(fn) + '.' + as_method(fn) + '(' + passed_params(fn) + ')') + ''';
                    } catch (Throwable exception) {
                        if (!handle(exception))
                            throw exception;
        ''', indent=1)
        if not void_functional(fn):
            output('return ' + raw_optional_return(fn) + '.empty();', indent=4)
        output('''\
                    }
                }
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            /**
             * Runs {@link ''' + fn + '''} in a try-catch block.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws, the exception is caught and passed to {@link #handle(Throwable)},
             * which applies exception handling policy (log, silence, ignore, custom).
             * {@link NullPointerException} from {@code null} {@code ''' + short_name(fn) + '''} is caught too.
        ''', indent=1)
        if void_functional(fn):
            output(' * This method then completes normally unless {@link #handle(Throwable)} requests a rethrow.', indent=1)
        else:
            output(' * This method then returns empty {@link ' + raw_optional_return(fn) + '} unless {@link #handle(Throwable)} requests a rethrow.', indent=1)
        output('''\
             * <p>
             * Typical usage: {@code Exceptions.log().''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> my_throwing_lambda)}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + fn + '''} to run''' + usually_a_lambda(fn) + '''
        ''', indent=1)
        if not void_functional(fn):
            output(' * @return an {@link ' + raw_optional_return(fn) + '} carrying {@code ' + short_name(fn)
                   + '} result or an empty {@link ' + raw_optional_return(fn) + '} if exception was caught', indent=1)
        output('''\
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + optional_type(return_type(fn)) + ' '
                + as_method(fn) + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                try {
                    ''' + return_if_needed(return_type(fn)) + optional_of_nullable(return_type(fn), short_name(fn) + '.' + as_method(fn) + '()') + ''';
                } catch (Throwable exception) {
                    if (!handle(exception))
                        throw exception;
        ''', indent=1)
        if not void_functional(fn):
            output('return ' + raw_optional_return(fn) + '.empty();', indent=3)
        output('''\
                }
            }
        ''', indent=1)
    output('}')

def filter_source():
    output(file_header())
    output('''\
        import java.util.*;
        import java.util.function.*;
        
        /**
         * Represents exception handling policy that always ends with throwing another exception.
         * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
         * Method {@link #handle(Throwable)} defines the exception handling policy when implemented in derived class.
         * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
         * <p>
         * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda)}
         * <p>
         * All wrapping methods surround the functional interface with a try-catch block.
         * If the functional interface throws, the exception is caught and passed to {@link #handle(Throwable)}.
         * {@link NullPointerException} from {@code null} functional interface is caught too.
         * Method {@link #handle(Throwable)} applies exception handling policy (log, count, ignore, etc.) and
         * throws a replacement or wrapping exception.
         * If it returns without throwing any exception, the original exception is rethrown.
         * <p>
         * Wrapping methods for all standard functional interfaces are provided.
         * Simple interfaces have short method names like {@link #runnable(Runnable)} or {@link #supplier(Supplier)}.
         * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(UnaryOperator)}.
         * Parameterless functional interfaces can be called directly by methods {@link #run(Runnable)}, {@link #get(Supplier)},
         * and the various {@code getAsX} variants.
         * 
         * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
         * @see #handle(Throwable)
         * @see ExceptionHandler#passing()
         * @see ExceptionHandler
         * @see CheckedExceptionHandler
         */
        public abstract class ExceptionFilter {
            /**
             * Handles exception in a generic way. This method must be defined in a derived class.
             * One built-in implementation is provided by {@link ExceptionHandler#passing()}.
             * All other methods of the {@code ExceptionFilter} call this method, but it can be also called directly.
             * <p>
             * This method represents reusable catch block that handles all exceptions in the same way.
             * When invoked, it must somehow handle the provided exception, for example by logging it.
             * It can also replace or wrap the exception by throwing a new exception.
             * If this method returns without throwing, it is a signal that the original exception should be rethrown.
             * All other methods of this class will rethrow in that case.
             * 
             * @param exception
             *            the exception to handle
             * @throws NullPointerException
             *             if {@code exception} is {@code null}
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public abstract void handle(Throwable exception);
            /**
             * Initializes new {@code ExceptionFilter}.
             */
            protected ExceptionFilter() {
            }
    ''')
    for fn in functional_types():
        def typical_usage():
            if fn == 'CloseableScope':
                return 'try (var scope = Exceptions.log().passing().closeable(openSomething()))'
            return 'methodTaking' + fn + '(Exceptions.log().passing().' + from_method(fn) + '(' + lambda_params(fn) + ' -> my_throwing_lambda))'
        output('''\
            /**
             * Applies exception filter to {@link ''' + fn + '''}.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
             * {@link NullPointerException} from {@code null} {@code ''' + short_name(fn) + '''} is caught too.
             * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
             * <p>
             * Typical usage: {@code ''' + typical_usage() + '''}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + fn + '''} to wrap''' + usually_a_lambda(fn) + '''
             * @return wrapper that runs {@link ''' + fn + '''} in a try-catch block
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + parameterized_type(fn) + ' '
                + from_method(fn) + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                return new Filtered''' + parameterized_type(fn) + '(' + short_name(fn) + ''');
            }
            private final class Filtered''' + parameterized_type(fn) + ' implements ' + parameterized_type(fn) + ''' {
                private final ''' + parameterized_type(fn) + ' ' + short_name(fn) + ''';
                Filtered''' + fn + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                    this.''' + short_name(fn) + ' = ' + short_name(fn) + ''';
                }
                @Override
                public ''' + return_type(fn) + ' ' + as_method(fn) + '(' + declared_params(fn) + ''') {
                    try {
                        ''' + return_if_needed(return_type(fn)) + short_name(fn) + '.' + as_method(fn) + '(' + passed_params(fn) + ''');
                    } catch (Throwable exception) {
                        handle(exception);
                        throw exception;
                    }
                }
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            /**
             * Filters exceptions while running {@link ''' + fn + '''}.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
             * {@link NullPointerException} from {@code null} {@code ''' + short_name(fn) + '''} is caught too.
             * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
             * <p>
             * Typical usage: {@code Exceptions.log().passing().''' + as_method(fn) + '''(() -> my_throwing_lambda))}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + fn + '''} to run''' + usually_a_lambda(fn) + '''
        ''', indent=1)
        if not void_functional(fn):
            output(' * @return value returned from {@code ' + short_name(fn) + '}', indent=1)
        output('''\
             * @throws NullPointerException
             *             if {@code ''' + short_name(fn) + '''} is {@code null}
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + return_type(fn) + ' '
                + as_method(fn) + '(' + parameterized_type(fn) + ' ' + short_name(fn) + ''') {
                try {
                    ''' + return_if_needed(return_type(fn)) + short_name(fn) + '.' + as_method(fn) + '''();
                } catch (Throwable exception) {
                    handle(exception);
                    throw exception;
                }
            }
        ''', indent=1)
    output('}')

def checked_source():
    output(file_header())
    output('''\
        import java.util.*;
        import java.util.function.*;
        import com.machinezoo.noexception.throwing.*;
        
        /**
         * Represents downgrading policy for checked exceptions.
         * The exception policy is akin to a reusable {@code catch} block that catches checked exception and throws an unchecked one.
         * Method {@link #handle(Exception)} defines downgrading mechanism, typically by wrapping the checked exception in an unchecked one,
         * but there are special cases like {@link Exceptions#sneak()}, which downgrade only method signature without altering the exception itself.
         * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
         * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
         * <p>
         * Typical usage: {@code Exceptions.sneak().get(() -> my_throwing_lambda)}
         * <p>
         * {@code CheckedExceptionHandler} does not stop propagation of any exceptions (checked or unchecked).
         * {@link ExceptionHandler} is used for that purpose.
         * The two classes can be used together by first downgrading checked exceptions with {@code CheckedExceptionHandler}
         * and then applying exception handling policy with {@link ExceptionHandler}.
         * <p>
         * Combined usage: {@code Exceptions.log().get(Exceptions.sneak().supplier(() -> my_throwing_lambda)).orElse(fallback)}
         * <p>
         * All wrapping methods surround the functional interface with a try-catch block.
         * Only checked exceptions are handled. Unchecked exceptions are propagated to caller.
         * If the functional interface throws checked exception, the exception is caught and passed to {@link #handle(Exception)},
         * which converts it to an unchecked exception, which is then thrown.
         * <p>
         * Wrapping methods for all standard functional interfaces are provided.
         * Simple interfaces have short method names, like {@link #runnable(ThrowingRunnable)} or {@link #supplier(ThrowingSupplier)}.
         * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(ThrowingUnaryOperator)}.
         * Parameterless functional interfaces can be called directly by methods {@link #run(ThrowingRunnable)}, {@link #get(ThrowingSupplier)},
         * and the various {@code getAsX} variants.
         * All methods take throwing versions of standard functional interfaces, for example {@link ThrowingRunnable} or {@link ThrowingSupplier}.
         * 
         * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
         * @see #handle(Exception)
         * @see Exceptions
         * @see ExceptionHandler
         * @see ExceptionFilter
         */
        public abstract class CheckedExceptionHandler {
            /**
             * Converts checked exception into an unchecked one. This method must be defined in a derived class.
             * Several implementations are provided by methods on {@link Exceptions} class.
             * All other methods of the {@code CheckedExceptionHandler} call this method, but it can be also called directly.
             * <p>
             * This method represents reusable catch block that handles all checked exceptions in the same way.
             * When invoked, it must somehow convert the checked exception into an unchecked one, usually by wrapping it.
             * Caller is then expected to throw the returned exception.
             * There can be special cases like {@link Exceptions#sneak()}, which don't return at all.
             * <p>
             * Callers should not pass in {@link RuntimeException} or other unchecked exceptions.
             * This method might erroneously wrap such exceptions as if they are checked exceptions.
             * Methods of this class never pass unchecked exceptions to this method.
             * 
             * @param exception
             *            checked exception to convert
             * @return converted unchecked exception
             * @throws NullPointerException
             *             if {@code exception} is {@code null}
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public abstract RuntimeException handle(Exception exception);
            /**
             * Initializes new {@code CheckedExceptionHandler}.
             */
            protected CheckedExceptionHandler() {
            }
    ''')
    for fn in functional_types():
        def typical_usage():
            if fn == 'CloseableScope':
                return 'try (var scope = Exceptions.sneak().closeable(openSomething()))'
            return 'methodTaking' + fn + '(Exceptions.sneak().' + from_method(fn) + '(' + lambda_params(fn) + ' -> my_throwing_lambda))'
        output('''\
            /**
             * Removes checked exceptions from method signature of {@link ''' + throwing_variant(fn) + '''}.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
             * which usually converts it to an unchecked exception, which is then thrown by this method.
             * Null {@code ''' + short_name(fn) + '''} is silently wrapped and causes {@link NullPointerException} when executed.
             * <p>
             * Typical usage: {@code ''' + typical_usage() + '''}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + throwing_variant(fn) + '''} to be converted''' + usually_a_lambda(fn) + '''
             * @return converted {@link ''' + fn + '''} free of checked exceptions
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + parameterized_type(fn) + ' '
                + from_method(fn) + '(' + parameterized_throwing_variant(fn) + ' ' + short_name(fn) + ''') {
                return new Checked''' + parameterized_type(fn) + '(' + short_name(fn) + ''');
            }
            private final class Checked''' + parameterized_type(fn) + ' implements ' + parameterized_type(fn) + ''' {
                private final ''' + parameterized_throwing_variant(fn) + ' ' + short_name(fn) + ''';
                Checked''' + fn + '(' + parameterized_throwing_variant(fn) + ' ' + short_name(fn) + ''') {
                    this.''' + short_name(fn) + ' = ' + short_name(fn) + ''';
                }
                @Override
                public ''' + return_type(fn) + ' ' + as_method(fn) + '(' + declared_params(fn) + ''') {
                    try {
                        ''' + return_if_needed(return_type(fn)) + short_name(fn) + '.' + as_method(fn) + '(' + passed_params(fn) + ''');
                    } catch (RuntimeException exception) {
                        throw exception;
                    } catch (Exception exception) {
                        throw handle(exception);
                    } catch (Throwable exception) {
                        throw SneakingHandler.sneak(exception);
                    }
                }
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            /**
             * Filters out checked exceptions while running {@link ''' + throwing_variant(fn) + '''}.
             * <p>
             * If {@code ''' + short_name(fn) + '''} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
             * which usually converts it to an unchecked exception, which is then thrown by this method.
             * <p>
             * Typical usage: {@code Exceptions.sneak().''' + as_method(fn) + '''(() -> my_throwing_lambda))}
             * 
             * @param ''' + short_name(fn) + '''
             *            the {@link ''' + throwing_variant(fn) + '''} to run''' + usually_a_lambda(fn) + '''
        ''', indent=1)
        if not void_functional(fn):
            output(' * @return value returned from {@code ' + short_name(fn) + '}', indent=1)
        output('''\
             * @throws NullPointerException
             *             if {@code ''' + short_name(fn) + '''} is {@code null}
             * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
             * @see Exceptions
             */
            public final''' + space_left(type_parameter_section(fn)) + ' ' + return_type(fn) + ' '
                + as_method(fn) + '(' + parameterized_throwing_variant(fn) + ' ' + short_name(fn) + ''') {
                try {
                    ''' + return_if_needed(return_type(fn)) + short_name(fn) + '.' + as_method(fn) + '''();
                } catch (RuntimeException exception) {
                    throw exception;
                } catch (Exception exception) {
                    throw handle(exception);
                } catch (Throwable exception) {
                    throw SneakingHandler.sneak(exception);
                }
            }
        ''', indent=1)
    output('}')

def throwing_test(fn):
    output(file_header('.throwing'))
    output('import java.io.*;')
    if 'Comparator' == fn:
        output('import java.util.*;')
    output('import java.util.concurrent.*;')
    if fn not in ['Runnable', 'Comparator']:
        output('import java.util.function.*;')
    body = '{\n        \t\t}' if void_functional(fn) else test_output(fn)
    output('''\
        import org.junit.jupiter.api.*;

        public class Throwing''' + fn + '''Test {
            void takeThrowing(Throwing''' + test_parameterized_type(fn) + ''' functional) {
            }
            void takeNonThrowing(''' + test_parameterized_type(fn) + ''' functional) {
            }
            @Test
            public void lambdas() {
                takeNonThrowing(''' + lambda_params(fn) + ' -> ' + body + ''');
                takeThrowing(''' + lambda_params(fn) + ' -> ' + body + ''');
                takeThrowing(''' + lambda_params(fn) + ''' -> {
                    if (ThreadLocalRandom.current().nextBoolean())
                        throw new IOException();
                    else
                        return''' + ('' if void_functional(fn) else ' ' + test_output(fn)) + ''';
                });
                Throwable throwable = new IOException();
                takeThrowing(''' + lambda_params(fn) + ''' -> {
                    if (ThreadLocalRandom.current().nextBoolean())
                        throw throwable;
                    else
                        return''' + ('' if void_functional(fn) else ' ' + test_output(fn)) + ''';
                });
            }
        }
    ''')

def defaults_test(fn, full, lazy):
    name = 'full' if full else 'empty'
    value = optional_test_output(fn) if full else raw_optional_return(fn) + '.empty()'
    expected = test_output(fn) if full else test_fallback(fn)
    type = 'Fallback' if lazy else 'Default'
    output('''\
        @Test
        public void ''' + name + '''() {
            ''' + test_unchecked(fn) + 'Optional' + test_parameterized_type(fn) + ' ' + name + ' = mock(Optional' + fn + '''.class);
            when(''' + name + '.' + method_verb(fn) + '(' + test_input(fn) + ')).thenReturn(' + value + ''');
    ''', indent=1)
    raw_return_supplier = raw_type(supplier_of(return_type(fn)))
    if lazy:
        unchecked_supplier = '@SuppressWarnings("unchecked") ' if not is_primitive(return_type(fn)) else ''
        output('''\
            ''' + unchecked_supplier + supplier_of(test_return_type(fn)) + ' fallback = mock(' + raw_return_supplier + '''.class);
            when(fallback.''' + as_method(raw_return_supplier) + '()).thenReturn(' + test_fallback(fn) + ''');
        ''', indent=2)
    output('''\
            assertEquals(''' + expected + ', new ' + type + test_parameterized_type(fn) + '(' + name + ', '
                + ('fallback' if lazy else test_fallback(fn)) + ').' + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
            verify(''' + name + ', only()).' + method_verb(fn) + '(' + test_input(fn) + ''');
    ''', indent=2)
    if lazy:
        if full:
            output('verifyNoMoreInteractions(fallback);', indent=2)
        else:
            output('verify(fallback, only()).' + as_method(raw_return_supplier) + '();', indent=2)
    output('}', indent=1)

def default_test(fn):
    output(file_header('.optional'))
    output('''\
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
    ''')
    if 'boolean' != return_type(fn):
        output('import java.util.*;')
    output('''\
        import org.junit.jupiter.api.*;
        
        public class Default''' + fn + '''Test {
    ''')
    defaults_test(fn, True, False)
    defaults_test(fn, False, False)
    output('}')

def fallback_test(fn):
    output(file_header('.optional'))
    output('''\
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
    ''')
    if 'boolean' != return_type(fn):
        output('import java.util.*;')
    output('''\
        import java.util.function.*;
        import org.junit.jupiter.api.*;
        
        public class Fallback''' + fn + '''Test {
    ''')
    defaults_test(fn, True, True)
    defaults_test(fn, False, True)
    output('}')

def optional_test(fn):
    output(file_header('.optional'))
    output('import static org.junit.jupiter.api.Assertions.*;')
    if 'boolean' != return_type(fn):
        output('import java.util.*;')
    output('''\
        import org.junit.jupiter.api.*;
        
        public class Optional''' + fn + '''Test {
            @Test
            public void conversions() {
                assertEquals(''' + optional_test_output(fn) + ', create(' + lambda_params(fn) + ' -> ' + optional_test_output(fn)
                    + ').' + method_verb(fn) + '(' + test_input(fn) + '''));
                assertEquals(''' + test_output(fn) + ', create(' + lambda_params(fn) + ' -> ' + optional_test_output(fn)
                    + ').orElse(' + test_fallback(fn) + ').' + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
                assertEquals(''' + test_output(fn) + ', create(' + lambda_params(fn) + ' -> ' + optional_test_output(fn)
                    + ').orElseGet(() -> ' + test_output(fn) + ').' + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
                assertEquals(''' + raw_optional_return(fn) + '.empty(), create(' + lambda_params(fn) + ' -> ' + raw_optional_return(fn)
                    + '.empty()).' + method_verb(fn) + '(' + test_input(fn) + '''));
                assertEquals(''' + test_fallback(fn) + ', create(' + lambda_params(fn) + ' -> ' + raw_optional_return(fn)
                    + '.empty()).orElse(' + test_fallback(fn) + ').' + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
                assertEquals(''' + test_fallback(fn) + ', create(' + lambda_params(fn) + ' -> ' + raw_optional_return(fn)
                    + '.empty()).orElseGet(() -> ''' + test_fallback(fn) + ').' + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
            }
            private Optional''' + test_parameterized_type(fn) + ' create(Optional' + test_parameterized_type(fn) + ''' lambda) {
                return lambda;
            }
        }
    ''')

def handler_test():
    output(file_header())
    output('''\
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.core.IsInstanceOf.*;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
        import java.util.*;
        import java.util.function.*;
        import org.junit.jupiter.api.*;
        import com.machinezoo.noexception.optional.*;
        
        public class ExceptionHandlerTest {
    ''')
    for fn in functional_types():
        output('''\
            @Test
            public void ''' + from_method(fn) + '''_complete() {
                ExceptionCollector collector = new ExceptionCollector(true);
                ''' + test_unchecked(fn) + test_parameterized_type(fn) + ' lambda = mock(' + fn + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + from_method(fn) + '(lambda).' + method_verb(fn) + '(' + test_input(fn) + ');', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '(' + test_input(fn) + ')).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + optional_test_output(fn)
                    + ', collector.' + from_method(fn) + '(lambda).' + method_verb(fn) + '(' + test_input(fn) + '''));
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '(' + test_input(fn) + ''');
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + from_method(fn) + '''_swallowException() {
                ExceptionCollector collector = new ExceptionCollector(true);
        ''', indent=1)
        if void_functional(fn):
            output('''\
                collector.''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + method_verb(fn) + '(' + test_input(fn) + ''');
            ''', indent=2)
        else:
            output('''\
                assertEquals(''' + raw_optional_return(fn) + '.empty(), collector.' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + method_verb(fn) + '(' + test_input(fn) + '''));
            ''', indent=2)
        output('''\
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
            @Test
            public void ''' + from_method(fn) + '''_passException() {
                ExceptionCollector collector = new ExceptionCollector(false);
                assertThrows(NumberFormatException.class, () -> collector.''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + method_verb(fn) + '(' + test_input(fn) + '''));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            @Test
            public void ''' + as_method(fn) + '''_complete() {
                ExceptionCollector collector = new ExceptionCollector(true);
                ''' + test_unchecked(fn) + test_parameterized_type(fn) + ' lambda = mock(' + fn + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + as_method(fn) + '(lambda);', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '()).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + optional_test_output(fn) + ', collector.' + as_method(fn) + '''(lambda));
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '(' + test_input(fn) + ''');
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + as_method(fn) + '''_swallowException() {
                ExceptionCollector collector = new ExceptionCollector(true);
        ''', indent=1)
        if void_functional(fn):
            output('''\
                collector.''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                });
            ''', indent=2)
        else:
            output('''\
                assertEquals(''' + raw_optional_return(fn) + '.empty(), collector.' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }));
            ''', indent=2)
        output('''\
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
            @Test
            public void ''' + as_method(fn) + '''_passException() {
                ExceptionCollector collector = new ExceptionCollector(false);
                assertThrows(NumberFormatException.class, () -> collector.''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
        ''', indent=1)
    output('}')

def filter_test():
    output(file_header())
    output('''\
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.core.IsInstanceOf.*;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
        import java.util.*;
        import java.util.function.*;
        import org.junit.jupiter.api.*;
        
        public class ExceptionFilterTest {
    ''')
    for fn in functional_types():
        output('''\
            @Test
            public void ''' + from_method(fn) + '''_complete() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(false);
                ''' + test_unchecked(fn) + test_parameterized_type(fn) + ' lambda = mock(' + fn + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + from_method(fn) + '(lambda).' + method_verb(fn) + '(' + test_input(fn) + ');', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '(' + test_input(fn) + ')).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + test_output(fn) + ', collector.' + from_method(fn) + '(lambda).' + as_method(fn) + '(' + test_input(fn) + '''));
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '(' + test_input(fn) + ''');
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + from_method(fn) + '''_rethrow() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(false);
                assertThrows(NumberFormatException.class, () -> collector.''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + as_method(fn) + '(' + test_input(fn) + '''));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
            @Test
            public void ''' + from_method(fn) + '''_replace() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(true);
                assertThrows(CollectedException.class, () -> collector.''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + as_method(fn) + '(' + test_input(fn) + '''));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            @Test
            public void ''' + as_method(fn) + '''_complete() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(false);
                ''' + test_unchecked(fn) + test_parameterized_type(fn) + ' lambda = mock(' + fn + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + as_method(fn) + '(lambda);', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '()).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + test_output(fn) + ', collector.' + as_method(fn) + '''(lambda));
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '(' + test_input(fn) + ''');
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + as_method(fn) + '''_pass() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(false);
                assertThrows(NumberFormatException.class, () -> collector.''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
            @Test
            public void ''' + as_method(fn) + '''_replace() {
                FilteredExceptionCollector collector = new FilteredExceptionCollector(true);
                assertThrows(CollectedException.class, () -> collector.''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }));
                assertThat(collector.single(), instanceOf(NumberFormatException.class));
            }
        ''', indent=1)
    output('}')

def checked_test():
    output(file_header())
    output('''\
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.core.IsInstanceOf.*;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;
        import java.awt.print.*;
        import java.util.*;
        import org.junit.jupiter.api.*;
        import com.machinezoo.noexception.throwing.*;
        
        public class CheckedExceptionHandlerTest {
    ''')
    for fn in functional_types():
        output('''\
            @Test
            public void ''' + from_method(fn) + '''_complete() throws Throwable {
                CheckedExceptionCollector collector = new CheckedExceptionCollector();
                ''' + test_unchecked(fn) + test_parameterized_throwing_variant(fn) + ' lambda = mock(' + throwing_variant(fn) + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + from_method(fn) + '(lambda).' + as_method(fn) + '(' + test_input(fn) + ');', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '(' + test_input(fn) + ')).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + test_output(fn) + ', collector.' + from_method(fn) + '(lambda).'
                    + as_method(fn) + '(' + test_input(fn) + ')' + test_precision(fn) + ''');
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '(' + test_input(fn) + ''');
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + from_method(fn) + '''_exception() {
                CheckedExceptionCollector collector = new CheckedExceptionCollector();
                assertThrows(CollectedException.class, () -> collector.''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new PrinterException();
                }).''' + as_method(fn) + '(' + test_input(fn) + '''));
                assertThat(collector.single(), instanceOf(PrinterException.class));
            }
            @Test
            public void ''' + from_method(fn) + '''_runtime() {
                assertThrows(NumberFormatException.class, () -> new CheckedExceptionCollector().''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }).''' + as_method(fn) + '(' + test_input(fn) + '''));
            }
            @Test
            public void ''' + from_method(fn) + '''_error() {
                assertThrows(ServiceConfigurationError.class, () -> new CheckedExceptionCollector().''' + from_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new ServiceConfigurationError("");
                }).''' + as_method(fn) + '(' + test_input(fn) + '''));
            }
        ''', indent=1)
    for fn in executable_functional_types():
        output('''\
            @Test
            public void ''' + as_method(fn) + '''_complete() throws Throwable {
                CheckedExceptionCollector collector = new CheckedExceptionCollector();
                ''' + test_unchecked(fn) + test_parameterized_throwing_variant(fn) + ' lambda = mock(' + throwing_variant(fn) + '''.class);
        ''', indent=1)
        if void_functional(fn):
            output('collector.' + as_method(fn) + '(lambda);', indent=2)
        else:
            output('''\
                when(lambda.''' + as_method(fn) + '()).thenReturn(' + test_output(fn) + ''');
                assertEquals(''' + test_output(fn) + ', collector.' + as_method(fn) + '(lambda)' + test_precision(fn) + ''');
            ''', indent=2)
        output('''\
                verify(lambda, only()).''' + as_method(fn) + '''();
                assertTrue(collector.empty());
            }
            @Test
            public void ''' + as_method(fn) + '''_exception() {
                CheckedExceptionCollector collector = new CheckedExceptionCollector();
                assertThrows(CollectedException.class, () -> collector.''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new PrinterException();
                }));
                assertThat(collector.single(), instanceOf(PrinterException.class));
            }
            @Test
            public void ''' + as_method(fn) + '''_runtime() {
                assertThrows(NumberFormatException.class, () -> new CheckedExceptionCollector().''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new NumberFormatException();
                }));
            }
            @Test
            public void ''' + as_method(fn) + '''_error() {
                assertThrows(ServiceConfigurationError.class, () -> new CheckedExceptionCollector().''' + as_method(fn) + '(' + lambda_params(fn) + ''' -> {
                    throw new ServiceConfigurationError("");
                }));
            }
        ''', indent=1)
    output('}')

# Finally, we run all the code generators.

for functional in functional_types():
    if functional not in ['CloseableScope']:
        redirect(sources + '/throwing/Throwing' + functional + '.java', lambda: throwing_source(functional))
        redirect(tests + '/throwing/Throwing' + functional + 'Test.java', lambda: throwing_test(functional))
for functional in nonvoid_functional_types():
    redirect(sources + '/optional/Default' + functional + '.java', lambda: default_source(functional))
    redirect(sources + '/optional/Fallback' + functional + '.java', lambda: fallback_source(functional))
    redirect(sources + '/optional/Optional' + functional + '.java', lambda: optional_source(functional))
    redirect(tests + '/optional/Default' + functional + 'Test.java', lambda: default_test(functional))
    redirect(tests + '/optional/Fallback' + functional + 'Test.java', lambda: fallback_test(functional))
    redirect(tests + '/optional/Optional' + functional + 'Test.java', lambda: optional_test(functional))
redirect(sources + '/ExceptionHandler.java', handler_source)
redirect(sources + '/ExceptionFilter.java', filter_source)
redirect(sources + '/CheckedExceptionHandler.java', checked_source)
redirect(tests + '/ExceptionHandlerTest.java', handler_test)
redirect(tests + '/ExceptionFilterTest.java', filter_test)
redirect(tests + '/CheckedExceptionHandlerTest.java', checked_test)
