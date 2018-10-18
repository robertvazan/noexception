// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalBiFunction} is typically obtained from {@link ExceptionHandler#fromBiFunction(BiFunction)},
 * in which case its return value is empty when the underlying {@code BiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiFunction}
 * @param <U>
 *            see {@link BiFunction}
 * @param <R>
 *            see {@link BiFunction}
 * @see ExceptionHandler#fromBiFunction(BiFunction)
 * @see BiFunction
 */
@FunctionalInterface public interface OptionalBiFunction<T, U, R> extends BiFunction<T, U, Optional<R>> {
	/**
	 * Variation of {@link BiFunction#apply(Object, Object)} that returns {@link Optional}.
	 * If this {@code OptionalBiFunction} is obtained from {@link ExceptionHandler#fromBiFunction(BiFunction)},
	 * the {@code Optional} will be empty only if the underlying {@code BiFunction} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code BiFunction} (possibly {@code null}).
	 * 
	 * @param t
	 *            see {@link BiFunction#apply(Object, Object)}
	 * @param u
	 *            see {@link BiFunction#apply(Object, Object)}
	 * @return {@code Optional} typically wrapping return value of {@link BiFunction#apply(Object, Object)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromBiFunction(BiFunction)
	 * @see BiFunction#apply(Object, Object)
	 */
	@Override Optional<R> apply(T t, U u);
	/**
	 * Convert this {@code OptionalBiFunction} to plain {@code BiFunction} using default value.
	 * The returned {@code BiFunction} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code BiFunction} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default BiFunction<T, U, R> orElse(R result) {
		return new DefaultBiFunction<T, U, R>(this, result);
	}
	/**
	 * Convert this {@code OptionalBiFunction} to plain {@code BiFunction} using fallback {@code Supplier}.
	 * The returned {@code BiFunction} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code BiFunction} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default BiFunction<T, U, R> orElseGet(Supplier<R> source) {
		return new FallbackBiFunction<T, U, R>(this, source);
	}
}
