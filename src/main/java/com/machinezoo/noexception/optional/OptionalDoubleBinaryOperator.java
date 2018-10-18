// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleBinaryOperator} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleBinaryOperator} is typically obtained from {@link ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)},
 * in which case its return value is empty when the underlying {@code DoubleBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)
 * @see DoubleBinaryOperator
 */
@FunctionalInterface public interface OptionalDoubleBinaryOperator {
	/**
	 * Variation of {@link DoubleBinaryOperator#applyAsDouble(double, double)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalDoubleBinaryOperator} is obtained from {@link ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code DoubleBinaryOperator} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code DoubleBinaryOperator}.
	 * 
	 * @param left
	 *            see {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @param right
	 *            see {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link DoubleBinaryOperator#applyAsDouble(double, double)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)
	 * @see DoubleBinaryOperator#applyAsDouble(double, double)
	 */
	OptionalDouble apply(double left, double right);
	/**
	 * Convert this {@code OptionalDoubleBinaryOperator} to plain {@code DoubleBinaryOperator} using default value.
	 * The returned {@code DoubleBinaryOperator} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code DoubleBinaryOperator} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleBinaryOperator orElse(double result) {
		return new DefaultDoubleBinaryOperator(this, result);
	}
	/**
	 * Convert this {@code OptionalDoubleBinaryOperator} to plain {@code DoubleBinaryOperator} using fallback {@code DoubleSupplier}.
	 * The returned {@code DoubleBinaryOperator} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code DoubleBinaryOperator} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleBinaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleBinaryOperator(this, source);
	}
}
