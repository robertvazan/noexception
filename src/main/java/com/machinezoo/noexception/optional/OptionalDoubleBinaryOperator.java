// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleBinaryOperator} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleBinaryOperator} is typically obtained from {@link ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)},
 * in which case its return value is empty when the underlying {@link DoubleBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)
 * @see DoubleBinaryOperator
 */
@FunctionalInterface
public interface OptionalDoubleBinaryOperator {
	/**
	 * Variation of {@link DoubleBinaryOperator#applyAsDouble(double, double)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalDoubleBinaryOperator} is obtained from {@link ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)},
	 * the {@link OptionalDouble} will be empty only if the underlying {@link DoubleBinaryOperator} throws.
	 * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link DoubleBinaryOperator}.
	 * 
	 * @param left
	 *            see {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @param right
	 *            see {@link DoubleBinaryOperator#applyAsDouble(double, double)}
 * @return {@link OptionalDouble} typically wrapping return value of {@link DoubleBinaryOperator#applyAsDouble(double, double)},
 *         or an empty {@link OptionalDouble} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleBinaryOperator(DoubleBinaryOperator)
 * @see DoubleBinaryOperator#applyAsDouble(double, double)
 */
	OptionalDouble apply(double left, double right);
	/**
	 * Converts this {@code OptionalDoubleBinaryOperator} to plain {@link DoubleBinaryOperator} using default value.
	 * The returned {@link DoubleBinaryOperator} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or return {@code result} if the {@link OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalDouble}
	 * @return plain {@link DoubleBinaryOperator} that either unwraps {@link OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleBinaryOperator orElse(double result) {
		return new DefaultDoubleBinaryOperator(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleBinaryOperator} to plain {@link DoubleBinaryOperator} using fallback {@link DoubleSupplier}.
	 * The returned {@link DoubleBinaryOperator} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
	 * @return plain {@link DoubleBinaryOperator} that either unwraps {@link OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleBinaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleBinaryOperator(this, source);
	}
}
