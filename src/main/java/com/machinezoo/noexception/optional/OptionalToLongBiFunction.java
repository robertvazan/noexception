// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongBiFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalToLongBiFunction} is typically obtained from {@link ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)},
 * in which case its return value is empty when the underlying {@link ToLongBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongBiFunction}
 * @param <U>
 *            see {@link ToLongBiFunction}
 * @see ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)
 * @see ToLongBiFunction
 */
@FunctionalInterface
public interface OptionalToLongBiFunction<T, U> extends BiFunction<T, U, OptionalLong> {
	/**
	 * Variation of {@link ToLongBiFunction#applyAsLong(Object, Object)} that returns {@link OptionalLong}.
	 * If this {@code OptionalToLongBiFunction} is obtained from {@link ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link ToLongBiFunction} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link ToLongBiFunction}.
	 * 
	 * @param t
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @param u
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
 * @return {@link OptionalLong} typically wrapping return value of {@link ToLongBiFunction#applyAsLong(Object, Object)},
 *         or an empty {@link OptionalLong} (typically signifying an exception)
 * @see ExceptionHandler#fromToLongBiFunction(ToLongBiFunction)
 * @see ToLongBiFunction#applyAsLong(Object, Object)
 */
	@Override
	OptionalLong apply(T t, U u);
	/**
	 * Converts this {@code OptionalToLongBiFunction} to plain {@link ToLongBiFunction} using default value.
	 * The returned {@link ToLongBiFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link ToLongBiFunction} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default ToLongBiFunction<T, U> orElse(long result) {
		return new DefaultToLongBiFunction<T, U>(this, result);
	}
	/**
	 * Converts this {@code OptionalToLongBiFunction} to plain {@link ToLongBiFunction} using fallback {@link LongSupplier}.
	 * The returned {@link ToLongBiFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link ToLongBiFunction} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default ToLongBiFunction<T, U> orElseGet(LongSupplier source) {
		return new FallbackToLongBiFunction<T, U>(this, source);
	}
}
