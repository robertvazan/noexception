// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalBiFunction} is typically obtained from {@link ExceptionHandler#fromBiFunction(BiFunction)},
 * in which case its return value is empty when the underlying {@link BiFunction} throws an exception.
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
@FunctionalInterface
public interface OptionalBiFunction<T, U, R> extends BiFunction<T, U, Optional<R>> {
	/**
	 * Variation of {@link BiFunction#apply(Object, Object)} that returns {@link Optional}.
	 * If this {@code OptionalBiFunction} is obtained from {@link ExceptionHandler#fromBiFunction(BiFunction)},
	 * the {@link Optional} will be empty only if the underlying {@link BiFunction} throws.
	 * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link BiFunction} (possibly {@code null}).
	 * 
	 * @param t
	 *            see {@link BiFunction#apply(Object, Object)}
	 * @param u
	 *            see {@link BiFunction#apply(Object, Object)}
 * @return {@link Optional} typically wrapping return value of {@link BiFunction#apply(Object, Object)},
 *         or an empty {@link Optional} (typically signifying an exception)
 * @see ExceptionHandler#fromBiFunction(BiFunction)
 * @see BiFunction#apply(Object, Object)
 */
	@Override
	Optional<R> apply(T t, U u);
	/**
	 * Converts this {@code OptionalBiFunction} to plain {@link BiFunction} using default value.
	 * The returned {@link BiFunction} will unwrap present value from the {@link Optional} if possible,
	 * or return {@code result} if the {@link Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link Optional}
	 * @return plain {@link BiFunction} that either unwraps {@link Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default BiFunction<T, U, R> orElse(R result) {
		return new DefaultBiFunction<T, U, R>(this, result);
	}
	/**
	 * Converts this {@code OptionalBiFunction} to plain {@link BiFunction} using fallback {@link Supplier}.
	 * The returned {@link BiFunction} will unwrap present value from the {@link Optional} if possible,
	 * or fall back to calling {@code source} if the {@link Optional} is empty.
	 * 
	 * @param source
	 *            {@link Supplier} to query for fallback value when {@link Optional} is empty
	 * @return plain {@link BiFunction} that either unwraps {@link Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default BiFunction<T, U, R> orElseGet(Supplier<R> source) {
		return new FallbackBiFunction<T, U, R>(this, source);
	}
}
