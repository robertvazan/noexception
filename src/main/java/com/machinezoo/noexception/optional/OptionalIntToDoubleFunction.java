// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntToDoubleFunction} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalIntToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)},
 * in which case its return value is empty when the underlying {@link IntToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)
 * @see IntToDoubleFunction
 */
@FunctionalInterface
public interface OptionalIntToDoubleFunction extends IntFunction<OptionalDouble> {
	/**
	 * Variation of {@link IntToDoubleFunction#applyAsDouble(int)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalIntToDoubleFunction} is obtained from {@link ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)},
	 * the {@link OptionalDouble} will be empty only if the underlying {@link IntToDoubleFunction} throws.
	 * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link IntToDoubleFunction}.
	 * 
	 * @param value
	 *            see {@link IntToDoubleFunction#applyAsDouble(int)}
 * @return {@link OptionalDouble} typically wrapping return value of {@link IntToDoubleFunction#applyAsDouble(int)},
 *         or an empty {@link OptionalDouble} (typically signifying an exception)
 * @see ExceptionHandler#fromIntToDoubleFunction(IntToDoubleFunction)
 * @see IntToDoubleFunction#applyAsDouble(int)
 */
	@Override
	OptionalDouble apply(int value);
	/**
	 * Converts this {@code OptionalIntToDoubleFunction} to plain {@link IntToDoubleFunction} using default value.
	 * The returned {@link IntToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or return {@code result} if the {@link OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalDouble}
	 * @return plain {@link IntToDoubleFunction} that either unwraps {@link OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default IntToDoubleFunction orElse(double result) {
		return new DefaultIntToDoubleFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalIntToDoubleFunction} to plain {@link IntToDoubleFunction} using fallback {@link DoubleSupplier}.
	 * The returned {@link IntToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
	 * @return plain {@link IntToDoubleFunction} that either unwraps {@link OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default IntToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackIntToDoubleFunction(this, source);
	}
}
