// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link IntToLongFunction} that returns {@code OptionalLong} instead of the raw value.
 * {@code OptionalIntToLongFunction} is typically obtained from {@link ExceptionHandler#fromIntToLongFunction(IntToLongFunction)},
 * in which case its return value is empty when the underlying {@code IntToLongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntToLongFunction(IntToLongFunction)
 * @see IntToLongFunction
 */
@FunctionalInterface public interface OptionalIntToLongFunction extends IntFunction<OptionalLong> {
	/**
	 * Variation of {@link IntToLongFunction#applyAsLong(int)} that returns {@code OptionalLong}.
	 * If this {@code OptionalIntToLongFunction} is obtained from {@link ExceptionHandler#fromIntToLongFunction(IntToLongFunction)},
	 * the {@code OptionalLong} will be empty only if the underlying {@code IntToLongFunction} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code IntToLongFunction}.
	 * 
	 * @param value
	 *            see {@link IntToLongFunction#apply(int)}
	 * @return {@code OptionalLong} typically wrapping return value of {@link IntToLongFunction#applyAsLong(int)},
	 *         or an empty {@code OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntToLongFunction(IntToLongFunction)
	 * @see IntToLongFunction#applyAsLong(int)
	 */
	@Override OptionalLong apply(int value);
	/**
	 * Convert this {@code OptionalIntToLongFunction} to plain {@code IntToLongFunction} using default value.
	 * The returned {@code IntToLongFunction} will unwrap present value from {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code IntToLongFunction} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default IntToLongFunction orElse(long result) {
		return new DefaultIntToLongFunction(this, result);
	}
	/**
	 * Convert this {@code OptionalIntToLongFunction} to plain {@code IntToLongFunction} using fallback {@code LongSupplier}.
	 * The returned {@code IntToLongFunction} will unwrap present value from {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code IntToLongFunction} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default IntToLongFunction orElseGet(LongSupplier source) {
		return new FallbackIntToLongFunction(this, source);
	}
}
