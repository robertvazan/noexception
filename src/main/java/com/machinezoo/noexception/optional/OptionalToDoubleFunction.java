// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleFunction} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)},
 * in which case its return value is empty when the underlying {@link ToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToDoubleFunction}
 * @see ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)
 * @see ToDoubleFunction
 */
@FunctionalInterface
public interface OptionalToDoubleFunction<T> extends Function<T, OptionalDouble> {
	/**
	 * Variation of {@link ToDoubleFunction#applyAsDouble(Object)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalToDoubleFunction} is obtained from {@link ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)},
	 * the {@link OptionalDouble} will be empty only if the underlying {@link ToDoubleFunction} throws.
	 * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link ToDoubleFunction}.
	 * 
	 * @param value
	 *            see {@link ToDoubleFunction#applyAsDouble(Object)}
 * @return {@link OptionalDouble} typically wrapping return value of {@link ToDoubleFunction#applyAsDouble(Object)},
 *         or an empty {@link OptionalDouble} (typically signifying an exception)
 * @see ExceptionHandler#fromToDoubleFunction(ToDoubleFunction)
 * @see ToDoubleFunction#applyAsDouble(Object)
 */
	@Override
	OptionalDouble apply(T value);
	/**
	 * Converts this {@code OptionalToDoubleFunction} to plain {@link ToDoubleFunction} using default value.
	 * The returned {@link ToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or return {@code result} if the {@link OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalDouble}
	 * @return plain {@link ToDoubleFunction} that either unwraps {@link OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default ToDoubleFunction<T> orElse(double result) {
		return new DefaultToDoubleFunction<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalToDoubleFunction} to plain {@link ToDoubleFunction} using fallback {@link DoubleSupplier}.
	 * The returned {@link ToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
	 * @return plain {@link ToDoubleFunction} that either unwraps {@link OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default ToDoubleFunction<T> orElseGet(DoubleSupplier source) {
		return new FallbackToDoubleFunction<T>(this, source);
	}
}
