// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link IntToDoubleFunction} that returns {@code OptionalDouble} instead of the raw value.
 * {@code OptionalIntToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)},
 * in which case its return value is empty when the underlying {@code IntToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)
 * @see IntToDoubleFunction
 */
@FunctionalInterface public interface OptionalIntToDoubleFunction extends IntFunction<OptionalDouble> {
	/**
	 * Variation of {@link IntToDoubleFunction#applyAsDouble(int)} that returns {@code OptionalDouble}.
	 * If this {@code OptionalIntToDoubleFunction} is obtained from {@link ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code IntToDoubleFunction} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code IntToDoubleFunction}.
	 * 
	 * @param value
	 *            see {@link IntToDoubleFunction#apply(int)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link IntToDoubleFunction#applyAsDouble(int)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)
	 * @see IntToDoubleFunction#applyAsDouble(int)
	 */
	@Override OptionalDouble apply(int value);
	/**
	 * Convert this {@code OptionalIntToDoubleFunction} to plain {@code IntToDoubleFunction} using default value.
	 * The returned {@code IntToDoubleFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code IntToDoubleFunction} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default IntToDoubleFunction orElse(double result) {
		return new DefaultIntToDoubleFunction(this, result);
	}
	/**
	 * Convert this {@code OptionalIntToDoubleFunction} to plain {@code IntToDoubleFunction} using fallback {@code DoubleSupplier}.
	 * The returned {@code IntToDoubleFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code IntToDoubleFunction} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default IntToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackIntToDoubleFunction(this, source);
	}
}
