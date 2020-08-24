// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BinaryOperator} that returns {@link Optional} instead of the raw value.
 * {@code OptionalBinaryOperator} is typically obtained from {@link ExceptionHandler#fromBinaryOperator(BinaryOperator)},
 * in which case its return value is empty when the underlying {@link BinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BinaryOperator}
 * @see ExceptionHandler#fromBinaryOperator(BinaryOperator)
 * @see BinaryOperator
 */
@FunctionalInterface
public interface OptionalBinaryOperator<T> extends BiFunction<T, T, Optional<T>> {
	/**
	 * Variation of {@link BinaryOperator#apply(Object, Object)} that returns {@link Optional}.
	 * If this {@code OptionalBinaryOperator} is obtained from {@link ExceptionHandler#fromBinaryOperator(BinaryOperator)},
	 * the {@link Optional} will be empty only if the underlying {@link BinaryOperator} throws.
	 * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link BinaryOperator} (possibly {@code null}).
	 * 
	 * @param left
	 *            see {@link BinaryOperator#apply(Object, Object)}
	 * @param right
	 *            see {@link BinaryOperator#apply(Object, Object)}
 * @return {@link Optional} typically wrapping return value of {@link BinaryOperator#apply(Object, Object)},
 *         or an empty {@link Optional} (typically signifying an exception)
 * @see ExceptionHandler#fromBinaryOperator(BinaryOperator)
 * @see BinaryOperator#apply(Object, Object)
 */
	@Override
	Optional<T> apply(T left, T right);
	/**
	 * Converts this {@code OptionalBinaryOperator} to plain {@link BinaryOperator} using default value.
	 * The returned {@link BinaryOperator} will unwrap present value from the {@link Optional} if possible,
	 * or return {@code result} if the {@link Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link Optional}
	 * @return plain {@link BinaryOperator} that either unwraps {@link Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default BinaryOperator<T> orElse(T result) {
		return new DefaultBinaryOperator<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalBinaryOperator} to plain {@link BinaryOperator} using fallback {@link Supplier}.
	 * The returned {@link BinaryOperator} will unwrap present value from the {@link Optional} if possible,
	 * or fall back to calling {@code source} if the {@link Optional} is empty.
	 * 
	 * @param source
	 *            {@link Supplier} to query for fallback value when {@link Optional} is empty
	 * @return plain {@link BinaryOperator} that either unwraps {@link Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default BinaryOperator<T> orElseGet(Supplier<T> source) {
		return new FallbackBinaryOperator<T>(this, source);
	}
}
