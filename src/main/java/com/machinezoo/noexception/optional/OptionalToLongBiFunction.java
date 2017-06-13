// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongBiFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalToLongBiFunction} is typically obtained from {@link ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)},
 * in which case its return value is empty when the underlying {@code ToLongBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongBiFunction}
 * @param <U>
 *            see {@link ToLongBiFunction}
 * @see ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)
 * @see ToLongBiFunction
 */
@FunctionalInterface public interface OptionalToLongBiFunction<T, U> extends BiFunction<T, U, OptionalLong> {
	/**
	 * Variation of {@link ToLongBiFunction#applyAsLong(Object, Object)} that returns {@link OptionalLong}.
	 * If this {@code OptionalToLongBiFunction} is obtained from {@link ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)},
	 * the {@code OptionalLong} will be empty only if the underlying {@code ToLongBiFunction} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code ToLongBiFunction}.
	 * 
	 * @param t
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @param u
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @return {@code OptionalLong} typically wrapping return value of {@link ToLongBiFunction#applyAsLong(Object, Object)},
	 *         or an empty {@code OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)
	 * @see ToLongBiFunction#applyAsLong(Object, Object)
	 */
	@Override OptionalLong apply(T t, U u);
	/**
	 * Convert this {@code OptionalToLongBiFunction} to plain {@code ToLongBiFunction} using default value.
	 * The returned {@code ToLongBiFunction} will unwrap present value from the {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code ToLongBiFunction} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default ToLongBiFunction<T, U> orElse(long result) {
		return new DefaultToLongBiFunction<T, U>(this, result);
	}
	/**
	 * Convert this {@code OptionalToLongBiFunction} to plain {@code ToLongBiFunction} using fallback {@code LongSupplier}.
	 * The returned {@code ToLongBiFunction} will unwrap present value from the {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code ToLongBiFunction} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default ToLongBiFunction<T, U> orElseGet(LongSupplier source) {
		return new FallbackToLongBiFunction<T, U>(this, source);
	}
}
