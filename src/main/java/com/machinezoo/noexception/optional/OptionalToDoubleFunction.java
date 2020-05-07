// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleFunction} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)},
 * in which case its return value is empty when the underlying {@code ToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToDoubleFunction}
 * @see ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)
 * @see ToDoubleFunction
 */
@FunctionalInterface public interface OptionalToDoubleFunction<T> extends Function<T, OptionalDouble> {
	/**
	 * Variation of {@link ToDoubleFunction#applyAsDouble(Object)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalToDoubleFunction} is obtained from {@link ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code ToDoubleFunction} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code ToDoubleFunction}.
	 * 
	 * @param value
	 *            see {@link ToDoubleFunction#applyAsDouble(Object)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link ToDoubleFunction#applyAsDouble(Object)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)
	 * @see ToDoubleFunction#applyAsDouble(Object)
	 */
	@Override OptionalDouble apply(T value);
	/**
	 * Converts this {@code OptionalToDoubleFunction} to plain {@code ToDoubleFunction} using default value.
	 * The returned {@code ToDoubleFunction} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code ToDoubleFunction} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default ToDoubleFunction<T> orElse(double result) {
		return new DefaultToDoubleFunction<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalToDoubleFunction} to plain {@code ToDoubleFunction} using fallback {@code DoubleSupplier}.
	 * The returned {@code ToDoubleFunction} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code ToDoubleFunction} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default ToDoubleFunction<T> orElseGet(DoubleSupplier source) {
		return new FallbackToDoubleFunction<T>(this, source);
	}
}
