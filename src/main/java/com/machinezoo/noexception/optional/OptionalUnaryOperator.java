// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link UnaryOperator} that returns {@link Optional} instead of the raw value.
 * {@code OptionalUnaryOperator} is typically obtained from {@link ExceptionHandler#fromUnaryOperator(UnaryOperator)},
 * in which case its return value is empty when the underlying {@code UnaryOperator} throws an exception.
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
	 * the {@code Optional} will be empty only if the underlying {@code UnaryOperator} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code UnaryOperator} (possibly {@code null}).
	 * 
	 * @param operand
	 *            see {@link UnaryOperator#apply(Object)}
	 * @return {@code Optional} typically wrapping return value of {@link UnaryOperator#apply(Object)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromUnaryOperator(UnaryOperator)
	 * @see UnaryOperator#apply(Object)
	 */
	@Override
	Optional<T> apply(T operand);
	/**
	 * Converts this {@code OptionalUnaryOperator} to plain {@code UnaryOperator} using default value.
	 * The returned {@code UnaryOperator} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code UnaryOperator} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default UnaryOperator<T> orElse(T result) {
		return new DefaultUnaryOperator<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalUnaryOperator} to plain {@code UnaryOperator} using fallback {@code Supplier}.
	 * The returned {@code UnaryOperator} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code UnaryOperator} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default UnaryOperator<T> orElseGet(Supplier<T> source) {
		return new FallbackUnaryOperator<T>(this, source);
	}
}
