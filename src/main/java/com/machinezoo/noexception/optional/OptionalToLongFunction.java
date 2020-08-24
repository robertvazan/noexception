// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalToLongFunction} is typically obtained from {@link ExceptionHandler#fromToLongFunction(ToLongFunction)},
 * in which case its return value is empty when the underlying {@link ToLongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongFunction}
 * @see ExceptionHandler#fromToLongFunction(ToLongFunction)
 * @see ToLongFunction
 */
@FunctionalInterface
public interface OptionalToLongFunction<T> extends Function<T, OptionalLong> {
	/**
	 * Variation of {@link ToLongFunction#applyAsLong(Object)} that returns {@link OptionalLong}.
	 * If this {@code OptionalToLongFunction} is obtained from {@link ExceptionHandler#fromToLongFunction(ToLongFunction)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link ToLongFunction} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link ToLongFunction}.
	 * 
	 * @param value
	 *            see {@link ToLongFunction#applyAsLong(Object)}
 * @return {@link OptionalLong} typically wrapping return value of {@link ToLongFunction#applyAsLong(Object)},
 *         or an empty {@link OptionalLong} (typically signifying an exception)
 * @see ExceptionHandler#fromToLongFunction(ToLongFunction)
 * @see ToLongFunction#applyAsLong(Object)
 */
	@Override
	OptionalLong apply(T value);
	/**
	 * Converts this {@code OptionalToLongFunction} to plain {@link ToLongFunction} using default value.
	 * The returned {@link ToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link ToLongFunction} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default ToLongFunction<T> orElse(long result) {
		return new DefaultToLongFunction<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalToLongFunction} to plain {@link ToLongFunction} using fallback {@link LongSupplier}.
	 * The returned {@link ToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link ToLongFunction} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default ToLongFunction<T> orElseGet(LongSupplier source) {
		return new FallbackToLongFunction<T>(this, source);
	}
}
