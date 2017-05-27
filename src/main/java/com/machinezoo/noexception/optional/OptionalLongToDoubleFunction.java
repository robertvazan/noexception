// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link LongToDoubleFunction} that returns {@code OptionalDouble} instead of the raw value.
 * {@code OptionalLongToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)},
 * in which case its return value is empty when the underlying {@code LongToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)
 * @see LongToDoubleFunction
 */
@FunctionalInterface public interface OptionalLongToDoubleFunction extends LongFunction<OptionalDouble> {
	/**
	 * Variation of {@link LongToDoubleFunction#applyAsDouble(long)} that returns {@code OptionalDouble}.
	 * If this {@code OptionalLongToDoubleFunction} is obtained from {@link ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code LongToDoubleFunction} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code LongToDoubleFunction}.
	 * 
	 * @param value
	 *            see {@link LongToDoubleFunction#apply(long)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link LongToDoubleFunction#applyAsDouble(long)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)
	 * @see LongToDoubleFunction#applyAsDouble(long)
	 */
	@Override OptionalDouble apply(long value);
	/**
	 * Convert this {@code OptionalLongToDoubleFunction} to plain {@code LongToDoubleFunction} using default value.
	 * The returned {@code LongToDoubleFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code LongToDoubleFunction} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default LongToDoubleFunction orElse(double result) {
		return new DefaultLongToDoubleFunction(this, result);
	}
	/**
	 * Convert this {@code OptionalLongToDoubleFunction} to plain {@code LongToDoubleFunction} using fallback {@code DoubleSupplier}.
	 * The returned {@code LongToDoubleFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code LongToDoubleFunction} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default LongToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackLongToDoubleFunction(this, source);
	}
}
