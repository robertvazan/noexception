// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Function} that returns {@link Optional} instead of the raw value.
 * {@code OptionalFunction} is typically obtained from {@link ExceptionHandler#function(Function)},
 * in which case its return value is empty when the underlying {@code Function} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Function}
 * @param <R>
 *            see {@link Function}
 * @see ExceptionHandler#function(Function)
 * @see Function
 */
@FunctionalInterface
public interface OptionalFunction<T, R> extends Function<T, Optional<R>> {
	/**
	 * Variation of {@link Function#apply(Object)} that returns {@link Optional}.
	 * If this {@code OptionalFunction} is obtained from {@link ExceptionHandler#function(Function)},
	 * the {@code Optional} will be empty only if the underlying {@code Function} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code Function} (possibly {@code null}).
	 * 
	 * @param t
	 *            see {@link Function#apply(Object)}
	 * @return {@code Optional} typically wrapping return value of {@link Function#apply(Object)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#function(Function)
	 * @see Function#apply(Object)
	 */
	@Override
	Optional<R> apply(T t);
	/**
	 * Converts this {@code OptionalFunction} to plain {@code Function} using default value.
	 * The returned {@code Function} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code Function} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default Function<T, R> orElse(R result) {
		return new DefaultFunction<T, R>(this, result);
	}
	/**
	 * Converts this {@code OptionalFunction} to plain {@code Function} using fallback {@code Supplier}.
	 * The returned {@code Function} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code Function} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default Function<T, R> orElseGet(Supplier<R> source) {
		return new FallbackFunction<T, R>(this, source);
	}
}
