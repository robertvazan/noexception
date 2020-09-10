// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link UnaryOperator} that returns {@link Optional} instead of the raw value.
 * {@code OptionalUnaryOperator} is typically obtained from {@link ExceptionHandler#fromUnaryOperator(UnaryOperator)},
 * in which case its return value is empty when the underlying {@link UnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link UnaryOperator}
 * @see ExceptionHandler#fromUnaryOperator(UnaryOperator)
 * @see UnaryOperator
 */
@FunctionalInterface
public interface OptionalUnaryOperator<T> extends Function<T, Optional<T>> {
	/**
	 * Variation of {@link UnaryOperator#apply(Object)} that returns {@link Optional}.
	 * If this {@code OptionalUnaryOperator} is obtained from {@link ExceptionHandler#fromUnaryOperator(UnaryOperator)},
	 * the {@link Optional} will be empty only if the underlying {@link UnaryOperator} throws.
	 * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link UnaryOperator} (possibly {@code null}).
	 * 
	 * @param operand
	 *            see {@link UnaryOperator#apply(Object)}
	 * @return {@link Optional} typically wrapping return value of {@link UnaryOperator#apply(Object)},
	 *         or an empty {@link Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromUnaryOperator(UnaryOperator)
	 * @see UnaryOperator#apply(Object)
	 */
	@Override
	Optional<T> apply(T operand);
	/**
	 * Converts this {@code OptionalUnaryOperator} to plain {@link UnaryOperator} using default value.
	 * The returned {@link UnaryOperator} will unwrap present value from the {@link Optional} if possible,
	 * or return {@code result} if the {@link Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link Optional}
	 * @return plain {@link UnaryOperator} that either unwraps {@link Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default UnaryOperator<T> orElse(T result) {
		return new DefaultUnaryOperator<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalUnaryOperator} to plain {@link UnaryOperator} using fallback {@link Supplier}.
	 * The returned {@link UnaryOperator} will unwrap present value from the {@link Optional} if possible,
	 * or fall back to calling {@code source} if the {@link Optional} is empty.
	 * 
	 * @param source
	 *            {@link Supplier} to query for fallback value when {@link Optional} is empty
	 * @return plain {@link UnaryOperator} that either unwraps {@link Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default UnaryOperator<T> orElseGet(Supplier<T> source) {
		return new FallbackUnaryOperator<T>(this, source);
	}
}
