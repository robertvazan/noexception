// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;
import org.slf4j.*;

/**
 * Static methods for creating predefined exception handlers.
 * Custom exception handlers can be created by inheriting from {@link ExceptionHandler} and {@link CheckedExceptionHandler}.
 * <p>
 * Typical usage: {@code Exceptions.silence().get(() -> my_throwing_lambda).orElse(fallback)}
 * <p>
 * Usage with checked exceptions: {@code Exceptions.silence().run(Exceptions.sneak().runnable(() -> my_throwing_lambda))}
 *
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 */
public final class Exceptions {
    private static final Logger logger = LoggerFactory.getLogger(Exceptions.class);
    private static final PropagatingHandler propagate = new PropagatingHandler();
    @Deprecated
    private static final LoggingHandler log = new LoggingHandler(logger, () -> "Caught exception.");
    private static final SilencingHandler silence = new SilencingHandler();
    private static final SneakingHandler sneak = new SneakingHandler();
    private static final WrappingHandler wrap = new WrappingHandler();
    private Exceptions() {}
    /**
     * Returns {@code ExceptionHandler} that propagates (lets through) all exceptions.
     * This exception handler is equivalent to having no exception handler at all.
     * It is useful when switching among several exception handlers at runtime.
     * 
     * @return propagating exception handler
     */
    public static ExceptionHandler propagate() { return propagate; }
    /**
     * Returns {@code ExceptionHandler} that lets all exceptions through.
     * This is an old deprecated alias for {@link #propagate()}.
     * 
     * @return pass-through exception handler
     * @deprecated Use {@link #propagate()} instead.
     */
    @Deprecated public static ExceptionHandler ignore() { return propagate(); }
    /**
     * Returns {@code ExceptionHandler} that lets all exceptions through.
     * This is an old deprecated alias for {@link #propagate()}.
     * 
     * @return pass-through exception handler
     * @deprecated Use {@link #propagate()} instead.
     */
    @Deprecated public static ExceptionHandler pass() { return propagate(); }
    /**
     * Returns {@code ExceptionHandler} that writes all exceptions to common logger.
     * Logs are written to SLF4J logger named after this class.
     * This handler is convenient and a suitable default choice,
     * but the single shared logger can make logs harder to filter.
     * Use {@link #log(Logger)} to specify custom logger where filtering is important.
     * <p>
     * Typical usage: {@code Exceptions.log().run(() -> my_throwing_lambda)}
     * <p>
     * No exceptions are allowed through, not even {@code Error}s.
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @return logging exception handler
     * @see #log(Logger)
     * @deprecated Use <a href="https://noexception.machinezoo.com/slf4j">SLF4J extension</a> instead.
     */
    @Deprecated public static ExceptionHandler log() { return log; }
    /**
     * Creates {@code ExceptionHandler} that writes all exceptions to the specified {@code logger}.
     * Most application code can use the more convenient {@link #log()} method.
     * Use {@link #log(Logger, String)} overload to specify unique message where necessary.
     * <p>
     * Typical usage: {@code Exceptions.log(logger).run(() -> my_throwing_lambda)}
     * <p>
     * No exceptions are allowed through, not even {@code Error}s.
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @param logger
     *            where all exceptions are logged
     * @return exception handler with custom logger
     * @throws NullPointerException
     *             if {@code logger} is {@code null}
     * @see #log()
     * @see #log(Logger, String)
     * @deprecated Use <a href="https://noexception.machinezoo.com/slf4j">SLF4J extension</a> instead.
     */
    @Deprecated public static ExceptionHandler log(Logger logger) { return new LoggingHandler(logger, () -> "Caught exception."); }
    /**
     * Creates {@code ExceptionHandler} that writes all exceptions to the specified {@code logger} with the specified {@code message}.
     * If you just need to specify custom logger, use {@link #log(Logger)}.
     * This overload allows for differentiating or explanatory message.
     * If the message is expensive to construct, use {@link #log(Logger, Supplier)} method.
     * <p>
     * Typical usage: {@code Exceptions.log(logger, "Failed to do X.").run(() -> my_throwing_lambda)}
     * <p>
     * No exceptions are allowed through, not even {@code Error}s.
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @param logger
     *            where all exceptions are logged
     * @param message
     *            introduces every exception in the log
     * @return exception handler with custom logger and message
     * @throws NullPointerException
     *             if {@code logger} or {@code message} is {@code null}
     * @see #log(Logger)
     * @see #log(Logger, Supplier)
     * @deprecated Use <a href="https://noexception.machinezoo.com/slf4j">SLF4J extension</a> instead.
     */
    @Deprecated public static ExceptionHandler log(Logger logger, String message) {
        Objects.requireNonNull(message);
        return new LoggingHandler(logger, () -> message);
    }
    /**
     * Creates {@code ExceptionHandler} that writes all exceptions to the specified {@code logger} with lazily evaluated {@code message}.
     * If the message does not need lazy evaluation, use the {@link #log(Logger, String)} method.
     * This overload constructs expensive messages lazily only when exception is caught.
     * If {@code message} throws, the exception is logged and fallback message is used to log the original exception.
     * <p>
     * Typical usage: {@code Exceptions.log(logger, () -> "Exception in " + this).run(() -> my_throwing_lambda)}
     * <p>
     * No exceptions are allowed through, not even {@code Error}s.
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @param logger
     *            where all exceptions are logged
     * @param message
     *            a {@link Supplier} that is lazily evaluated to generate log message
     * @return exception handler with custom logger and lazily evaluated message
     * @throws NullPointerException
     *             if {@code logger} or {@code message} is {@code null}
     * @see #log(Logger, String)
     * @deprecated Use <a href="https://noexception.machinezoo.com/slf4j">SLF4J extension</a> instead.
     */
    @Deprecated public static ExceptionHandler log(Logger logger, Supplier<String> message) { return new LoggingHandler(logger, message); }
    /**
     * Returns {@code ExceptionHandler} that silently ignores all exceptions.
     * This handler is useful when some code is known to produce junk exceptions.
     * Most application code should use logging or counting handler instead.
     * <p>
     * Typical usage: {@code Exceptions.silence().run(() -> my_throwing_lambda)}
     * <p>
     * No exceptions are allowed through, not even {@code Error}s.
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @return exception handler that ignores all exceptions
     */
    public static ExceptionHandler silence() { return silence; }
    /**
     * Returns {@code CheckedExceptionHandler} that lets through checked exceptions without declaring them.
     * All exceptions are allowed through unmodified, including checked ones, even though no checked exceptions are declared.
     * <p>
     * Typical usage: {@code Exceptions.sneak().run(() -> my_throwing_lambda)}
     * <p>
     * This is the recommended {@code CheckedExceptionHandler}, because it does not obfuscate the original exception in any way.
     * Use {@link #wrap()} if using sneaky exceptions is not possible or not desirable.
     * <p>
     * Sneaky throw is implemented using a neat trick with type erasure
     * that persuades java compiler to tolerate throwing undeclared checked exception from specially crafted generic method.
     * The trick is safe. It is legal java code and JVMs do not mind it. It is just considered dirty by some developers.
     * 
     * @return exception handler that lets checked exceptions through
     * @see #wrap()
     */
    public static CheckedExceptionHandler sneak() { return sneak; }
    /**
     * Returns {@code CheckedExceptionHandler} that wraps all checked exceptions.
     * Unchecked exceptions are passed through unmodified.
     * Checked exceptions are wrapped in {@link UndeclaredThrowableException}.
     * Use {@link #sneak()} to avoid wrapping.
     * Use {@link #wrap(Function)} to specify a custom wrapper.
     * <p>
     * Typical usage: {@code Exceptions.wrap().run(() -> my_throwing_lambda)}
     * <p>
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
     * 
     * @return exception handler that wraps checked exceptions
     * @see #sneak()
     * @see #wrap(Function)
     */
    public static CheckedExceptionHandler wrap() { return wrap; }
    /**
     * Creates {@code CheckedExceptionHandler} that applies custom wrapper to checked exceptions.
     * Unchecked exceptions are passed through unmodified.
     * Checked exceptions are passed to {@code wrapper} and the resulting unchecked exception is thrown.
     * Use {@link #sneak()} to avoid wrapping and {@link #wrap()} to use standard wrapper.
     * <p>
     * Typical usage: {@code Exceptions.wrap(MyWrapperException::new).run(() -> my_throwing_lambda)}
     * <p>
     * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called before invoking the {@code wrapper}.
     * 
     * @param wrapper
     *            method converting checked exception into an unchecked one, often just exception constructor reference
     * @return exception handler with custom exception transform
     * @throws NullPointerException
     *             if {@code wrapper} is {@code null}
     * @see #sneak()
     * @see #wrap()
     */
    public static CheckedExceptionHandler wrap(Function<Exception, RuntimeException> wrapper) { return new MappingHandler(wrapper); }
}
