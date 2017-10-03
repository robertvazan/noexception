// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.function.*;
import org.slf4j.*;

/**
 * Static methods for creating predefined exception handlers.
 * Custom exception handlers can be created by inheriting from {@link ExceptionHandler} and {@link CheckedExceptionHandler}.
 * <p>
 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda).orElse(fallback)}
 * <p>
 * Usage with checked exceptions: {@code Exceptions.log().run(Exceptions.sneak().runnable(() -> my_throwing_lambda))}
 * 
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 */
public final class Exceptions {
	private static final Logger logger = LoggerFactory.getLogger(Exceptions.class);
	private static final ExceptionPassThrough pass = new ExceptionPassThrough();
	private static final ExceptionLogger log = new ExceptionLogger(logger, "Caught exception");
	private static final ExceptionSilencer silence = new ExceptionSilencer();
	private static final ExceptionSmuggler sneak = new ExceptionSmuggler();
	private static final ExceptionWrapper wrap = new ExceptionWrapper();
	private Exceptions() {
	}
	/**
	 * Get {@code ExceptionHandler} that lets all exceptions through.
	 * This exception handler is equivalent to having no exception handler at all.
	 * It is useful when switching among several exception handlers at runtime.
	 * 
	 * @return pass-through exception handler
	 */
	public static ExceptionHandler pass() {
		return pass;
	}
	/**
	 * Get {@code ExceptionHandler} that writes all exceptions to common logger.
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
	 */
	public static ExceptionHandler log() {
		return log;
	}
	/**
	 * Create {@code ExceptionHandler} that writes all exceptions to the specified {@code logger}.
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
	 */
	public static ExceptionHandler log(Logger logger) {
		return new ExceptionLogger(logger, "Caught exception");
	}
	/**
	 * Create {@code ExceptionHandler} that writes all exceptions to the specified {@code logger} with the specified {@code message}.
	 * Most application code can use the more convenient {@link #log()} method.
	 * This overload allows for differentiating or explanatory message.
	 * If you just need to specify custom logger, use {@link #log(Logger)}.
	 * <p>
	 * Typical usage: {@code Exceptions.log(logger, "Caught exception").run(() -> my_throwing_lambda)}
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
	 * @see #log()
	 * @see #log(Logger)
	 */
	public static ExceptionHandler log(Logger logger, String message) {
		return new ExceptionLogger(logger, message);
	}
	/**
	 * Get {@code ExceptionHandler} that silently ignores all exceptions.
	 * This handler is useful when some code is known to produce junk exceptions.
	 * Most application code should use {@link #log()} instead.
	 * <p>
	 * Typical usage: {@code Exceptions.silence().run(() -> my_throwing_lambda)}
	 * <p>
	 * No exceptions are allowed through, not even {@code Error}s.
	 * If {@link InterruptedException} is caught, {@link Thread#interrupt()} is called.
	 * 
	 * @return exception handler that ignores all exceptions
	 * @see #log()
	 */
	public static ExceptionHandler silence() {
		return silence;
	}
	/**
	 * Get {@code CheckedExceptionHandler} that lets through checked exceptions without declaring them.
	 * All exceptions are allowed through unmodified, including checked ones, even though no checked exceptions are declared.
	 * This exception handler works the same way as lombok's <a href="https://projectlombok.org/features/SneakyThrows.html">SneakyThrows</a>
	 * and it is actually implemented using said lombok feature.
	 * This is the recommended {@code CheckedExceptionHandler}, because it does not obfuscate the original exception in any way.
	 * Use {@link #wrap()} if using this method is not possible.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().run(() -> my_throwing_lambda)}
	 * 
	 * @return exception handler that lets checked exceptions through
	 * @see #wrap()
	 */
	public static CheckedExceptionHandler sneak() {
		return sneak;
	}
	/**
	 * Get {@code CheckedExceptionHandler} that wraps all checked exceptions.
	 * Unchecked exceptions are passed through unmodified.
	 * Checked exceptions are wrapped in {@link WrappedException}.
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
	public static CheckedExceptionHandler wrap() {
		return wrap;
	}
	/**
	 * Create {@code CheckedExceptionHandler} that applies custom wrapper to checked exceptions.
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
	public static CheckedExceptionHandler wrap(Function<Exception, RuntimeException> wrapper) {
		return new ExceptionTransform(wrapper);
	}
}
