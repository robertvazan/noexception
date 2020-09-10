// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntToLongFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalIntToLongFunction} is typically obtained from {@link ExceptionHandler#fromIntToLongFunction(IntToLongFunction)},
 * in which case its return value is empty when the underlying {@link IntToLongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntToLongFunction(IntToLongFunction)
 * @see IntToLongFunction
 */
@FunctionalInterface
public interface OptionalIntToLongFunction extends IntFunction<OptionalLong> {
	/**
	 * Variation of {@link IntToLongFunction#applyAsLong(int)} that returns {@link OptionalLong}.
	 * If this {@code OptionalIntToLongFunction} is obtained from {@link ExceptionHandler#fromIntToLongFunction(IntToLongFunction)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link IntToLongFunction} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link IntToLongFunction}.
	 * 
	 * @param value
	 *            see {@link IntToLongFunction#applyAsLong(int)}
	 * @return {@link OptionalLong} typically wrapping return value of {@link IntToLongFunction#applyAsLong(int)},
	 *         or an empty {@link OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntToLongFunction(IntToLongFunction)
	 * @see IntToLongFunction#applyAsLong(int)
	 */
	@Override
	OptionalLong apply(int value);
	/**
	 * Converts this {@code OptionalIntToLongFunction} to plain {@link IntToLongFunction} using default value.
	 * The returned {@link IntToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link IntToLongFunction} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default IntToLongFunction orElse(long result) {
		return new DefaultIntToLongFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalIntToLongFunction} to plain {@link IntToLongFunction} using fallback {@link LongSupplier}.
	 * The returned {@link IntToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link IntToLongFunction} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default IntToLongFunction orElseGet(LongSupplier source) {
		return new FallbackIntToLongFunction(this, source);
	}
}
