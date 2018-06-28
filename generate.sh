#!/bin/bash -euE
# Part of NoException: https://noexception.machinezoo.com
set -o pipefail
export SHELLOPTS
dest=`dirname $0`/src/main/java/com/machinezoo/noexception
tdest=`dirname $0`/src/test/java/com/machinezoo/noexception
function from-method {
	if [ ${1,} == ${1,,} ]; then
		echo ${1,}
	else
		echo from$1
	fi
}
function return-type {
	case $1 in
	Runnable | *Consumer)
		echo void;;
	Supplier | UnaryOperator | BinaryOperator)
		echo T;;
	IntSupplier | *ToInt* | Int*Operator | Comparator)
		echo int;;
	LongSupplier | *ToLong* | Long*Operator)
		echo long;;
	DoubleSupplier | *ToDouble* | Double*Operator)
		echo double;;
	BooleanSupplier | *Predicate)
		echo boolean;;
	*Function)
		echo R;;
	*)
		exit 1;;
	esac
}
function method-verb {
	case $1 in
	Runnable)
		echo run;;
	*Supplier)
		echo get;;
	*Consumer)
		echo accept;;
	*Function | *Operator)
		echo apply;;
	*Predicate)
		echo test;;
	Comparator)
		echo compare;;
	*)
		exit 1;;
	esac
}
function as-method {
	verb=`method-verb $1`
	case $verb in
	test | compare)
		echo $verb
		exit
	esac
	case `return-type $1` in
	int)
		echo ${verb}AsInt;;
	long)
		echo ${verb}AsLong;;
	double)
		echo ${verb}AsDouble;;
	boolean)
		echo ${verb}AsBoolean;;
	*)
		echo $verb;;
	esac
}
function declared-params {
	case $1 in
	Runnable | *Supplier)
		;;
	BiConsumer | BiPredicate | *BiFunction)
		echo "T t, U u";;
	Consumer | Predicate | Function)
		echo T t;;
	To*Function)
		echo T value;;
	IntConsumer | IntPredicate | Int*Function)
		echo int value;;
	LongConsumer | LongPredicate | Long*Function)
		echo long value;;
	DoubleConsumer | DoublePredicate | Double*Function)
		echo double value;;
	ObjIntConsumer)
		echo "T t, int value";;
	ObjLongConsumer)
		echo "T t, long value";;
	ObjDoubleConsumer)
		echo "T t, double value";;
	UnaryOperator)
		echo T operand;;
	IntUnaryOperator)
		echo int operand;;
	LongUnaryOperator)
		echo long operand;;
	DoubleUnaryOperator)
		echo double operand;;
	BinaryOperator | Comparator)
		echo "T left, T right";;
	IntBinaryOperator)
		echo "int left, int right";;
	LongBinaryOperator)
		echo "long left, long right";;
	DoubleBinaryOperator)
		echo "double left, double right";;
	*)
		exit 1;;
	esac
}
function passed-params {
	declared-params $1 | sed -re 's/[a-zA-Z]+ ([a-z]+)/\1/g'
}
function erased-params {
	declared-params $1 | sed -re 's/([a-zA-Z]+) [a-z]+/\1/g' | sed -re 's/T|U|R/Object/g'
}
function param-names {
	passed-params $1 | tr -d ','
}
function lambda-params {
	shortened=
	for name in `param-names $1`; do
		if [ "$shortened" != "" ]; then
			shortened="$shortened, "
		fi
		shortened="$shortened${name:0:1}"
	done
	if [[ "$shortened" == *","* ]]; then
		echo "($shortened)"
	elif [ "$shortened" == "" ]; then
		echo "()"
	else
		echo $shortened
	fi
}
function type-signature {
	case $1 in
	Function)
		echo "<T, R>";;
	BiFunction)
		echo "<T, U, R>";;
	BiConsumer | BiPredicate | To*BiFunction)
		echo "<T, U>";;
	Consumer | Predicate | Supplier | To*Function | Obj*Consumer | UnaryOperator | BinaryOperator | Comparator)
		echo "<T>";;
	*To*Function)
		;;
	*Function)
		echo "<R>";;
	Runnable | *Supplier | *Consumer | *Predicate | *Operator)
		;;
	*)
		exit 1;;
	esac
}
function type-params {
	type-signature $1 | tr -d '<>,'
}
function extends-type {
	if [ $1 == UnaryOperator ]; then
		echo "Function<T, T>"
	elif [ $1 == BinaryOperator ]; then
		echo "BiFunction<T, T, T>"
	fi
}
function erased-return {
	return-type $1 | sed -re 's/T|U|R/Object/g'
}
function supplier-of {
	case $1 in
	int | long | double | boolean)
		echo ${1^}Supplier;;
	*)
		echo "Supplier<$1>";;
	esac
}
function erased-supplier-of {
	case $1 in
	int | long | double | boolean)
		echo ${1^}Supplier;;
	*)
		echo "Supplier";;
	esac
}
function supplier-of-return {
	supplier-of `return-type $1`
}
function erased-supplier-of-return {
	erased-supplier-of `return-type $1`
}
function optional-of {
	case $1 in
	void)
		echo void;;
	int | long | double | boolean)
		echo Optional${1^};;
	*)
		echo "Optional<$1>";;
	esac
}
function optional-class-of {
	case $1 in
	void)
		echo void;;
	int | long | double | boolean)
		echo Optional${1^};;
	*)
		echo Optional;;
	esac
}
function optional-by-return-type {
	optional-class-of `return-type $1`
}
function make-optional-nullable {
	type=`return-type $1`
	case $type in
	void)
		echo "$2";;
	int | long | double | boolean)
		echo "Optional${type^}.of($2)";;
	*)
		echo "Optional.ofNullable($2)";;
	esac
}
function make-optional {
	type=`return-type $1`
	case $type in
	void)
		echo "$2";;
	int | long | double | boolean)
		echo "Optional${type^}.of($2)";;
	*)
		echo "Optional.of($2)";;
	esac
}
function short-name {
	case $1 in
	*Function)
		echo function;;
	*Consumer)
		echo consumer;;
	*Predicate)
		echo predicate;;
	*Supplier)
		echo supplier;;
	*Operator)
		echo operator;;
	Runnable)
		echo runnable;;
	Comparator)
		echo comparator;;
	*)
		exit 1;;
	esac
}
function space-left {
	if [ "$1" != "" ]; then
		echo " $1"
	fi
}
function is-void {
	[ `return-type $1` == void ]
}
function return-if-needed {
	if ! is-void $1; then
		echo "return "
	fi
}
function test-value {
	case `return-type $1` in
	void)
		;;
	int)
		echo 2;;
	long)
		echo 2L;;
	double)
		echo 2.0;;
	boolean)
		echo true;;
	*)
		echo '"value"';;
	esac
}
function test-default {
	case `return-type $1` in
	void)
		;;
	int)
		echo 3;;
	long)
		echo 3L;;
	double)
		echo 3.0;;
	boolean)
		echo false;;
	*)
		echo '"default"';;
	esac
}
function test-type-signature {
	type-signature $1 | sed -re 's/T|U|R/String/g'
}
function test-return-type {
	return-type $1 | sed -re 's/T|U|R/String/g'
}
function suppress-unchecked {
	if [ "`type-signature $1`" != "" ]; then
		echo '@SuppressWarnings("unchecked") '
	fi
}
function test-input {
	case "`declared-params $1 | sed -re 's/([a-zA-Z]+) [a-z]+/\1/g'`" in
	'')
		;;
	int)
		echo 1;;
	long)
		echo 1L;;
	double)
		echo 1.0;;
	'int, int')
		echo "11, 12";;
	'long, long')
		echo "11L, 12L";;
	'double, double')
		echo "1.1, 1.2";;
	'T, int')
		echo '"input", 1';;
	'T, long')
		echo '"input", 1L';;
	'T, double')
		echo '"input", 1.0';;
	T)
		echo '"input"';;
	'T, U' | 'T, T')
		echo '"input1", "input2"';;
	*)
		exit 1;;
	esac
}
function test-precision {
	if [ `return-type $1` == double ]; then
		echo ", 0.1"
	fi
}
function functional-types {
	echo Runnable
	echo Supplier
	for type in Int Long Double Boolean; do
		echo ${type}Supplier
	done
	echo Consumer
	for type in Int Long Double; do
		echo ${type}Consumer
	done
	echo BiConsumer
	for type in Int Long Double; do
		echo Obj${type}Consumer
	done
	echo Predicate
	for type in Int Long Double; do
		echo ${type}Predicate
	done
	echo BiPredicate
	echo Function
	for type in Int Long Double; do
		echo To${type}Function
		echo ${type}Function
		for type2 in Int Long Double; do
			if [ $type != $type2 ]; then
				echo ${type}To${type2}Function
			fi
		done
	done
	echo UnaryOperator
	for type in Int Long Double; do
		echo ${type}UnaryOperator
	done
	echo BiFunction
	for type in Int Long Double; do
		echo To${type}BiFunction
	done
	echo BinaryOperator
	for type in Int Long Double; do
		echo ${type}BinaryOperator
	done
	echo Comparator
}
function parameterless-types {
	for type in `functional-types`; do
		if [ "`declared-params $type`" == "" ]; then
			echo $type
		fi
	done
}
function valued-types {
	for type in `functional-types`; do
		if ! is-void $type; then
			echo $type
		fi
	done
}
function throwing-extends {
	extends=`extends-type $1`
	if [ "$extends" != "" ]; then
		echo " extends Throwing$extends"
	fi
}
function throwing {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

EOF
	if [ $1 != Runnable -a $1 != Comparator ]; then
		echo "import java.util.function.*;"
	fi
	cat <<EOF
import com.machinezoo.noexception.*;

/**
 * Variation of {@link $1} that allows throwing checked exceptions.
 * {@code Throwing$1} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#`from-method $1`(Throwing$1)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
EOF
	for typeparam in `type-params $1`; do
		cat <<EOF
 * @param <$typeparam>
 *            see {@link $1}
EOF
	done
	cat <<EOF
 * @see CheckedExceptionHandler#`from-method $1`(Throwing$1)
 * @see $1
 */
@FunctionalInterface public interface Throwing$1`type-signature $1``throwing-extends $1` {
EOF
	if [ "`extends-type $1`" == "" ]; then
		cat <<EOF
	/**
	 * Variation of {@link $1#`as-method $1`(`erased-params $1`)} that allows throwing checked exceptions.
	 * 
EOF
		for paramname in `param-names $1`; do
			cat <<EOF
	 * @param $paramname
	 *            see {@link $1#`as-method $1`(`erased-params $1`)}
EOF
		done
		if ! is-void $1; then
			echo -e "\t * @return see {@link $1#`as-method $1`(`erased-params $1`)}"
		fi
		cat <<EOF
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#`from-method $1`(Throwing$1)
	 * @see $1#`as-method $1`(`erased-params $1`)
	 */
	`return-type $1` `as-method $1`(`declared-params $1`) throws Exception;
EOF
	fi
	echo "}"
}
for type in `functional-types`; do
	throwing $type >$dest/throwing/Throwing$type.java
done
function optional-super {
	case $1 in
	Supplier)
		echo "Supplier<Optional<T>>";;
	*Supplier)
		if [[ $1 =~ (.*)Supplier ]]; then
			echo "Supplier<Optional${BASH_REMATCH[1]}>"
		fi
		;;
	Function)
		echo "Function<T, Optional<R>>";;
	BiFunction)
		echo "BiFunction<T, U, Optional<R>>";;
	To*BiFunction)
		if [[ $1 =~ To(.*)BiFunction ]]; then
			echo "BiFunction<T, U, Optional${BASH_REMATCH[1]}>"
		fi
		;;
	To*Function)
		if [[ $1 =~ To(.*)Function ]]; then
			echo "Function<T, Optional${BASH_REMATCH[1]}>"
		fi
		;;
	*To*Function)
		if [[ $1 =~ (.*)To(.*)Function ]]; then
			echo "${BASH_REMATCH[1]}Function<Optional${BASH_REMATCH[2]}>"
		fi
		;;
	*Function)
		if [[ $1 =~ (.*)Function ]]; then
			echo "${BASH_REMATCH[1]}Function<Optional<R>>"
		fi
		;;
	UnaryOperator)
		echo "Function<T, Optional<T>>";;
	*UnaryOperator)
		if [[ $1 =~ (.*)UnaryOperator ]]; then
			echo "${BASH_REMATCH[1]}Function<Optional${BASH_REMATCH[1]}>"
		fi
		;;
	BinaryOperator)
		echo "BiFunction<T, T, Optional<T>>";;
	*BinaryOperator | *Predicate)
		;;
	*)
		exit 1;;
	esac
}
function optional-extends {
	super=`optional-super $1`
	if [ "$super" != "" ]; then
		echo " extends $super"
	fi
}
function optional-override {
	if [ "`optional-super $1`" != "" ]; then
		echo "@Override "
	fi
}
function optional-possibly-null {
	case `return-type $1` in
	int|long|double|boolean)
		;;
	*)
		echo " (possibly {@code null})";;
	esac
}
function optional-default {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

EOF
	if [ $1 == Comparator ]; then
		echo "import java.util.*;"
	else
		echo "import java.util.function.*;"
	fi
	cat <<EOF

final class Default$1`type-signature $1` implements $1`type-signature $1` {
	private final Optional$1`type-signature $1` inner;
	private final `return-type $1` result;
	public Default$1(Optional$1`type-signature $1` inner, `return-type $1` result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public `return-type $1` `as-method $1`(`declared-params $1`) {
		return inner.`method-verb $1`(`passed-params $1`).orElse(result);
	}
}
EOF
}
function optional-fallback {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

EOF
	if [ $1 == Comparator ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import java.util.function.*;

final class Fallback$1`type-signature $1` implements $1`type-signature $1` {
	private final Optional$1`type-signature $1` inner;
	private final `supplier-of-return $1` source;
	public Fallback$1(Optional$1`type-signature $1` inner, `supplier-of-return $1` source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public `return-type $1` `as-method $1`(`declared-params $1`) {
		return inner.`method-verb $1`(`passed-params $1`).orElseGet(source);
	}
}
EOF
}
function optional {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

EOF
	if [ `return-type $1` != boolean ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link $1} that returns {@link `optional-by-return-type $1`} instead of the raw value.
 * {@code Optional$1} is typically obtained from {@link ExceptionHandler#`from-method $1`($1)},
 * in which case its return value is empty when the underlying {@code $1} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
EOF
	for typeparam in `type-params $1`; do
		cat <<EOF
 * @param <$typeparam>
 *            see {@link $1}
EOF
	done
	cat <<EOF
 * @see ExceptionHandler#`from-method $1`($1)
 * @see $1
 */
@FunctionalInterface public interface Optional$1`type-signature $1``optional-extends $1` {
	/**
	 * Variation of {@link $1#`as-method $1`(`erased-params $1`)} that returns {@link `optional-by-return-type $1`}.
	 * If this {@code Optional$1} is obtained from {@link ExceptionHandler#`from-method $1`($1)},
	 * the {@code `optional-by-return-type $1`} will be empty only if the underlying {@code $1} throws.
	 * Otherwise the returned {@code `optional-by-return-type $1`} just wraps the return value of underlying {@code $1}`optional-possibly-null $1`.
	 * 
EOF
	for paramname in `param-names $1`; do
		cat <<EOF
	 * @param $paramname
	 *            see {@link $1#`as-method $1`(`erased-params $1`)}
EOF
	done
	cat <<EOF
	 * @return {@code `optional-by-return-type $1`} typically wrapping return value of {@link $1#`as-method $1`(`erased-params $1`)},
	 *         or an empty {@code `optional-by-return-type $1`} (typically signifying an exception)
	 * @see ExceptionHandler#`from-method $1`($1)
	 * @see $1#`as-method $1`(`erased-params $1`)
	 */
	`optional-override $1``optional-of $(return-type $1)` `method-verb $1`(`declared-params $1`);
	/**
	 * Convert this {@code Optional$1} to plain {@code $1} using default value.
	 * The returned {@code $1} will unwrap present value from the {@code `optional-by-return-type $1`} if possible,
	 * or return {@code result} if the {@code `optional-by-return-type $1`} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code `optional-by-return-type $1`}
	 * @return plain {@code $1} that either unwraps {@code `optional-by-return-type $1`} or returns default value
	 * @see #orElseGet(`erased-supplier-of-return $1`)
	 * @see `optional-by-return-type $1`#orElse(`erased-return $1`)
	 */
	default $1`type-signature $1` orElse(`return-type $1` result) {
		return new Default$1`type-signature $1`(this, result);
	}
	/**
	 * Convert this {@code Optional$1} to plain {@code $1} using fallback {@code `erased-supplier-of-return $1`}.
	 * The returned {@code $1} will unwrap present value from the {@code `optional-by-return-type $1`} if possible,
	 * or fall back to calling {@code source} if the {@code `optional-by-return-type $1`} is empty.
	 * 
	 * @param source
	 *            {@code `erased-supplier-of-return $1`} to query for fallback value when {@code `optional-by-return-type $1`} is empty
	 * @return plain {@code $1} that either unwraps {@code `optional-by-return-type $1`} or falls back to {@code source}
	 * @see #orElse(`erased-return $1`)
	 * @see `optional-by-return-type $1`#orElseGet(`erased-supplier-of-return $1`)
	 */
	default $1`type-signature $1` orElseGet(`supplier-of-return $1` source) {
		return new Fallback$1`type-signature $1`(this, source);
	}
}
EOF
}
for type in `valued-types`; do
	optional-default $type >$dest/optional/Default$type.java
	optional-fallback $type >$dest/optional/Fallback$type.java
	optional $type >$dest/optional/Optional$type.java
done
function catch-interface {
	if is-void $1; then
		echo "$1`type-signature $1`"
	else
		echo "Optional$1`type-signature $1`"
	fi
}
function catch-orelse {
	if ! is-void $1; then
		echo ".orElse(fallback)"
	fi
}
function catch-type {
	cat <<EOF
	/**
	 * Wraps {@code $1} in a try-catch block.
	 * <p>
	 * If {@code `short-name $1`} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, ignore, pass, custom).
	 * {@code NullPointerException} from null {@code `short-name $1`} is caught too.
EOF
	if is-void $1; then
		cat <<EOF
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
EOF
	else
		cat <<EOF
	 * Wrapper then returns empty {@code `optional-by-return-type $1`} unless {@link #handle(Throwable)} requests a rethrow.
EOF
	fi
	cat <<EOF
	 * <p>
	 * Typical usage: {@code Exceptions.log().`from-method $1`(`lambda-params $1` -> my_throwing_lambda)`catch-orelse $1`}
	 * 
	 * @param `short-name $1`
	 *            the {@code $1} to wrap, usually a lambda
	 * @return wrapper that runs {@code `short-name $1`} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final`space-left "$(type-signature $1)"` `catch-interface $1` `from-method $1`($1`type-signature $1` `short-name $1`) {
		return new Catching$1`type-signature $1`(`short-name $1`);
	}
	private final class Catching$1`type-signature $1` implements `catch-interface $1` {
		private final $1`type-signature $1` `short-name $1`;
		Catching$1($1`type-signature $1` `short-name $1`) {
			this.`short-name $1` = `short-name $1`;
		}
		@Override public `optional-of $(return-type $1)` `method-verb $1`(`declared-params $1`) {
			try {
				`return-if-needed $1``make-optional-nullable $1 "$(short-name $1).$(as-method $1)($(passed-params $1))"`;
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
EOF
	if ! is-void $1; then
		cat <<EOF
				return `optional-by-return-type $1`.empty();
EOF
	fi
	cat <<EOF
			}
		}
	}
EOF
}
function catch-method {
	cat <<EOF
	/**
	 * Runs {@code $1} in a try-catch block.
	 * <p>
	 * If {@code `short-name $1`} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, ignore, pass, custom).
	 * {@code NullPointerException} from null {@code `short-name $1`} is caught too.
EOF
	if is-void $1; then
		cat <<EOF
	 * This method then completes normally unless {@link #handle(Throwable)} requests a rethrow.
EOF
	else
		cat <<EOF
	 * This method then returns empty {@code `optional-by-return-type $1`} unless {@link #handle(Throwable)} requests a rethrow.
EOF
	fi
	cat <<EOF
	 * <p>
	 * Typical usage: {@code Exceptions.log().`as-method $1`(`lambda-params $1` -> my_throwing_lambda)}
	 * 
	 * @param `short-name $1`
	 *            the {@code $1} to run, usually a lambda
EOF
	if ! is-void $1; then
		cat <<EOF
	 * @return an {@code `optional-by-return-type $1`} carrying {@code `short-name $1`} result or an empty {@code `optional-by-return-type $1`} if exception was caught
EOF
	fi
	cat <<EOF
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final`space-left "$(type-signature $1)"` `optional-of $(return-type $1)` `as-method $1`($1`type-signature $1` `short-name $1`) {
		try {
			`return-if-needed $1``make-optional-nullable $1 "$(short-name $1).$(as-method $1)()"`;
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
EOF
	if ! is-void $1; then
		cat <<EOF
			return `optional-by-return-type $1`.empty();
EOF
	fi
	cat <<EOF
		}
	}
EOF
}
function write-handler {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception;

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
 * which applies exception handling policy (log, ignore, pass, custom).
 * {@code NullPointerException} from null functional interface is caught too.
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
 * that is identical to its non-optional variant from JDK except it returns {@code Optional} instead of raw value.
 * This {@code Optional} is empty in case of exception.
 * Callers can use {@link Optional#orElse(Object)} and {@link Optional#orElseGet(Supplier)} and their
 * equivalents on {@code OptionalX} interfaces to provide fallback values.
 * 
 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
 * @see #handle(Throwable)
 * @see Exceptions
 * @see OptionalSupplier
 * @see Optional
 * @see CheckedExceptionHandler
 */
public abstract class ExceptionHandler {
	/**
	 * Handle exception in a generic way. This method must be defined in a derived class.
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
EOF
	for type in `functional-types`; do
		catch-type $type
	done
	for type in `parameterless-types`; do
		catch-method $type
	done
	echo "}"
}
write-handler >$dest/ExceptionHandler.java
function checked-type {
	cat <<EOF
	/**
	 * Remove checked exceptions from method signature of {@code $1}.
	 * <p>
	 * If {@code `short-name $1`} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code `short-name $1`} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTaking$1(Exceptions.sneak().`from-method $1`(`lambda-params $1` -> my_throwing_lambda))}
	 * 
	 * @param `short-name $1`
	 *            the {@code Throwing$1} to be converted, usually a lambda
	 * @return converted {@code $1} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final`space-left "$(type-signature $1)"` $1`type-signature $1` `from-method $1`(Throwing$1`type-signature $1` `short-name $1`) {
		return new Checked$1`type-signature $1`(`short-name $1`);
	}
	private final class Checked$1`type-signature $1` implements $1`type-signature $1` {
		private final Throwing$1`type-signature $1` `short-name $1`;
		Checked$1(Throwing$1`type-signature $1` `short-name $1`) {
			this.`short-name $1` = `short-name $1`;
		}
		@Override public `return-type $1` `as-method $1`(`declared-params $1`) {
			try {
				`return-if-needed $1``short-name $1`.`as-method $1`(`passed-params $1`);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
EOF
}
function checked-method {
	cat <<EOF
	/**
	 * Filter out checked exceptions while running {@code $1}.
	 * <p>
	 * If {@code `short-name $1`} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().`as-method $1`(() -> my_throwing_lambda))}
	 * 
	 * @param `short-name $1`
	 *            the {@code Throwing$1} to be converted, usually a lambda
EOF
	if ! is-void $1; then
		cat <<EOF
	 * @return value returned from {@code `short-name $1`}
EOF
	fi
	cat <<EOF
	 * @throws NullPointerException
	 *             if {@code `short-name $1`} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final`space-left "$(type-signature $1)"` `return-type $1` `as-method $1`(Throwing$1`type-signature $1` `short-name $1`) {
		try {
			`return-if-needed $1``short-name $1`.`as-method $1`();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
EOF
}
function write-checked {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception;

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
 * and then applying exception handling policy with {@code ExceptionHandler}.
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
 */
public abstract class CheckedExceptionHandler {
	/**
	 * Convert checked exception into an unchecked one. This method must be defined in a derived class.
	 * Several implementations are provided by methods on {@link Exceptions} class.
	 * All other methods of the {@code CheckedExceptionHandler} call this method, but it can be also called directly.
	 * <p>
	 * This method represents reusable catch block that handles all checked exceptions in the same way.
	 * When invoked, it must somehow convert the checked exception into an unchecked one, usually by wrapping it.
	 * Caller is then expected to throw the returned exception.
	 * There can be special cases like {@link Exceptions#sneak()}, which don't return at all.
	 * <p>
	 * Callers should not pass in {@code RuntimeException} or other unchecked exceptions.
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
	 * Initialize new {@code CheckedExceptionHandler}.
	 */
	protected CheckedExceptionHandler() {
	}
EOF
	for type in `functional-types`; do
		checked-type $type
	done
	for type in `parameterless-types`; do
		checked-method $type
	done
	echo "}"
}
write-checked >$dest/CheckedExceptionHandler.java
function throwing-test-body {
	if is-void $1; then
		echo -e "{\n\t\t}"
	else
		test-value $1
	fi
}
function throwing-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
EOF
	if [ $1 == Comparator ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import java.util.concurrent.*;
EOF
	if [ $1 != Runnable -a $1 != Comparator ]; then
		echo "import java.util.function.*;"
	fi
	cat <<EOF
import org.junit.*;

public class Throwing$1Test {
	void takeThrowing(Throwing$1`test-type-signature $1` functional) {
	}
	void takeNonThrowing($1`test-type-signature $1` functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(`lambda-params $1` -> `throwing-test-body $1`);
		takeThrowing(`lambda-params $1` -> `throwing-test-body $1`);
		takeThrowing(`lambda-params $1` -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return `test-value $1`;
		});
	}
}
EOF
}
for type in `functional-types`; do
	throwing-test $type >$tdest/throwing/Throwing${type}Test.java
done
function optional-default-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
EOF
	if [ `return-type $1` != boolean ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import org.junit.*;

public class Default$1Test {
	@Test public void full() {
		`suppress-unchecked $1`Optional$1`test-type-signature $1` full = mock(Optional$1.class);
		when(full.`method-verb $1`(`test-input $1`)).thenReturn(`make-optional $1 "$(test-value $1)"`);
		assertEquals(`test-value $1`, new Default$1`test-type-signature $1`(full, `test-default $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
		verify(full, only()).`method-verb $1`(`test-input $1`);
	}
	@Test public void empty() {
		`suppress-unchecked $1`Optional$1`test-type-signature $1` empty = mock(Optional$1.class);
		when(empty.`method-verb $1`(`test-input $1`)).thenReturn(`optional-by-return-type $1`.empty());
		assertEquals(`test-default $1`, new Default$1`test-type-signature $1`(empty, `test-default $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
		verify(empty, only()).`method-verb $1`(`test-input $1`);
	}
}
EOF
}
function optional-fallback-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
EOF
	if [ `return-type $1` != boolean ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import java.util.function.*;
import org.junit.*;

public class Fallback$1Test {
	@Test public void full() {
		`suppress-unchecked $1`Optional$1`test-type-signature $1` full = mock(Optional$1.class);
		when(full.`method-verb $1`(`test-input $1`)).thenReturn(`make-optional $1 "$(test-value $1)"`);
		`suppress-unchecked $(erased-supplier-of $(test-return-type $1))``supplier-of $(test-return-type $1)` fallback = mock(`erased-supplier-of-return $1`.class);
		when(fallback.`as-method $(erased-supplier-of-return $1)`()).thenReturn(`test-default $1`);
		assertEquals(`test-value $1`, new Fallback$1`test-type-signature $1`(full, fallback).`as-method $1`(`test-input $1`)`test-precision $1`);
		verify(full, only()).`method-verb $1`(`test-input $1`);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		`suppress-unchecked $1`Optional$1`test-type-signature $1` empty = mock(Optional$1.class);
		when(empty.`method-verb $1`(`test-input $1`)).thenReturn(`optional-by-return-type $1`.empty());
		`suppress-unchecked $(erased-supplier-of $(test-return-type $1))``supplier-of $(test-return-type $1)` fallback = mock(`erased-supplier-of-return $1`.class);
		when(fallback.`as-method $(erased-supplier-of-return $1)`()).thenReturn(`test-default $1`);
		assertEquals(`test-default $1`, new Fallback$1`test-type-signature $1`(empty, fallback).`as-method $1`(`test-input $1`)`test-precision $1`);
		verify(empty, only()).`method-verb $1`(`test-input $1`);
		verify(fallback, only()).`as-method $(erased-supplier-of-return $1)`();
	}
}
EOF
}
function optional-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
EOF
	if [ `return-type $1` != boolean ]; then
		echo "import java.util.*;"
	fi
	cat <<EOF
import org.junit.*;

public class Optional$1Test {
	@Test public void conversions() {
		assertEquals(`make-optional $1 $(test-value $1)`, create(`lambda-params $1` -> `make-optional $1 $(test-value $1)`).`method-verb $1`(`test-input $1`));
		assertEquals(`test-value $1`, create(`lambda-params $1` -> `make-optional $1 $(test-value $1)`).orElse(`test-default $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
		assertEquals(`test-value $1`, create(`lambda-params $1` -> `make-optional $1 $(test-value $1)`).orElseGet(() -> `test-value $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
		assertEquals(`optional-by-return-type $1`.empty(), create(`lambda-params $1` -> `optional-by-return-type $1`.empty()).`method-verb $1`(`test-input $1`));
		assertEquals(`test-default $1`, create(`lambda-params $1` -> `optional-by-return-type $1`.empty()).orElse(`test-default $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
		assertEquals(`test-default $1`, create(`lambda-params $1` -> `optional-by-return-type $1`.empty()).orElseGet(() -> `test-default $1`).`as-method $1`(`test-input $1`)`test-precision $1`);
	}
	private Optional$1`test-type-signature $1` create(Optional$1`test-type-signature $1` lambda) {
		return lambda;
	}
}
EOF
}
for type in `valued-types`; do
	optional-default-test $type >$tdest/optional/Default${type}Test.java
	optional-fallback-test $type >$tdest/optional/Fallback${type}Test.java
	optional-test $type >$tdest/optional/Optional${type}Test.java
done
function catch-type-test {
	cat <<EOF
	@Test public void `from-method $1`_complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		`suppress-unchecked $1`$1`test-type-signature $1` lambda = mock($1.class);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`from-method $1`(lambda).`method-verb $1`(`test-input $1`);
EOF
	else
		cat <<EOF
		when(lambda.`as-method $1`(`test-input $1`)).thenReturn(`test-value $1`);
		assertEquals(`make-optional $1 $(test-value $1)`, collector.`from-method $1`(lambda).`method-verb $1`(`test-input $1`));
EOF
	fi
		cat <<EOF
		verify(lambda, only()).`as-method $1`(`test-input $1`);
		assertTrue(collector.empty());
	}
	@Test public void `from-method $1`_swallowException() {
		ExceptionCollector collector = new ExceptionCollector(true);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`from-method $1`(`lambda-params $1` -> {
			throw new NumberFormatException();
		}).`method-verb $1`(`test-input $1`);
EOF
	else
		cat <<EOF
		assertEquals(`optional-by-return-type $1`.empty(), collector.`from-method $1`(`lambda-params $1` -> {
			throw new NumberFormatException();
		}).`method-verb $1`(`test-input $1`));
EOF
	fi
		cat <<EOF
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void `from-method $1`_passException() {
		ExceptionCollector collector = new ExceptionCollector(false);
		try {
			collector.`from-method $1`(`lambda-params $1` -> {
				throw new NumberFormatException();
			}).`method-verb $1`(`test-input $1`);
			fail();
		} catch (NumberFormatException e) {
		}
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
EOF
}
function catch-method-test {
	cat <<EOF
	@Test public void `as-method $1`_complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		`suppress-unchecked $1`$1`test-type-signature $1` lambda = mock($1.class);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`as-method $1`(lambda);
EOF
	else
		cat <<EOF
		when(lambda.`as-method $1`()).thenReturn(`test-value $1`);
		assertEquals(`make-optional $1 $(test-value $1)`, collector.`as-method $1`(lambda));
EOF
	fi
		cat <<EOF
		verify(lambda, only()).`as-method $1`(`test-input $1`);
		assertTrue(collector.empty());
	}
	@Test public void `as-method $1`_swallowException() {
		ExceptionCollector collector = new ExceptionCollector(true);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`as-method $1`(`lambda-params $1` -> {
			throw new NumberFormatException();
		});
EOF
	else
		cat <<EOF
		assertEquals(`optional-by-return-type $1`.empty(), collector.`as-method $1`(`lambda-params $1` -> {
			throw new NumberFormatException();
		}));
EOF
	fi
		cat <<EOF
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void `as-method $1`_passException() {
		ExceptionCollector collector = new ExceptionCollector(false);
		try {
			collector.`as-method $1`(`lambda-params $1` -> {
				throw new NumberFormatException();
			});
			fail();
		} catch (NumberFormatException e) {
		}
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
EOF
}
function exception-handler-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;
import com.machinezoo.noexception.optional.*;

public class ExceptionHandlerTest {
EOF
	for type in `functional-types`; do
		catch-type-test $type
	done
	for type in `parameterless-types`; do
		catch-method-test $type
	done
	echo "}"
}
exception-handler-test >$tdest/ExceptionHandlerTest.java
function checked-type-test {
	cat <<EOF
	@Test public void `from-method $1`_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		`suppress-unchecked $1`Throwing$1`test-type-signature $1` lambda = mock(Throwing$1.class);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`from-method $1`(lambda).`as-method $1`(`test-input $1`);
EOF
	else
		cat <<EOF
		when(lambda.`as-method $1`(`test-input $1`)).thenReturn(`test-value $1`);
		assertEquals(`test-value $1`, collector.`from-method $1`(lambda).`as-method $1`(`test-input $1`)`test-precision $1`);
EOF
	fi
		cat <<EOF
		verify(lambda, only()).`as-method $1`(`test-input $1`);
		assertTrue(collector.empty());
	}
	@Test public void `from-method $1`_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.`from-method $1`(`lambda-params $1` -> {
				throw new PrinterException();
			}).`as-method $1`(`test-input $1`);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
EOF
}
function checked-method-test {
	cat <<EOF
	@Test public void `as-method $1`_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		`suppress-unchecked $1`Throwing$1`test-type-signature $1` lambda = mock(Throwing$1.class);
EOF
	if is-void $1; then
		cat <<EOF
		collector.`as-method $1`(lambda);
EOF
	else
		cat <<EOF
		when(lambda.`as-method $1`()).thenReturn(`test-value $1`);
		assertEquals(`test-value $1`, collector.`as-method $1`(lambda)`test-precision $1`);
EOF
	fi
		cat <<EOF
		verify(lambda, only()).`as-method $1`();
		assertTrue(collector.empty());
	}
	@Test public void `as-method $1`_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.`as-method $1`(`lambda-params $1` -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
EOF
}
function checked-exception-handler-test {
	cat <<EOF
// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.print.*;
import org.junit.*;
import com.machinezoo.noexception.throwing.*;

public class CheckedExceptionHandlerTest {
EOF
	for type in `functional-types`; do
		checked-type-test $type
	done
	for type in `parameterless-types`; do
		checked-method-test $type
	done
	echo "}"
}
checked-exception-handler-test >$tdest/CheckedExceptionHandlerTest.java

