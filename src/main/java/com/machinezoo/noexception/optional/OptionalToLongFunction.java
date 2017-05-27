// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link ToLongFunction} that returns {@code OptionalLong} instead of the raw value.
 * {@code OptionalToLongFunction} is typically obtained from {@link ExceptionHandler#fromToLongFunction(ToLongFunction)},
 * in which case its return value is empty when the underlying {@code ToLongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongFunction}
 * @see ExceptionHandler#fromToLongFunction(ToLongFunction)
 * @see ToLongFunction
 */
@FunctionalInterface public interface OptionalToLongFunction<T> extends Function<T, OptionalLong> {
	/**
	 * Variation of {@link ToLongFunction#applyAsLong(Object)} that returns {@code OptionalLong}.
	 * If this {@code OptionalToLongFunction} is obtained from {@link ExceptionHandler#fromToLongFunction(ToLongFunction)},
	 * the {@code OptionalLong} will be empty only if the underlying {@code ToLongFunction} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code ToLongFunction}.
	 * 
	 * @param t
	 *            see {@link ToLongFunction#apply(Object)}
	 * @return {@code OptionalLong} typically wrapping return value of {@link ToLongFunction#applyAsLong(Object)},
	 *         or an empty {@code OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromToLongFunction(ToLongFunction)
	 * @see ToLongFunction#applyAsLong(Object)
	 */
	@Override OptionalLong apply(T t);
	/**
	 * Convert this {@code OptionalToLongFunction} to plain {@code ToLongFunction} using default value.
	 * The returned {@code ToLongFunction} will unwrap present value from {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code ToLongFunction} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default ToLongFunction<T> orElse(long result) {
		return new DefaultToLongFunction<T>(this, result);
	}
	/**
	 * Convert this {@code OptionalToLongFunction} to plain {@code ToLongFunction} using fallback {@code LongSupplier}.
	 * The returned {@code ToLongFunction} will unwrap present value from {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code ToLongFunction} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default ToLongFunction<T> orElseGet(LongSupplier source) {
		return new FallbackToLongFunction<T>(this, source);
	}
}
