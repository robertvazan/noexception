// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link DoubleUnaryOperator} that returns {@code OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleUnaryOperator} is typically obtained from {@link ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)},
 * in which case its return value is empty when the underlying {@code DoubleUnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)
 * @see DoubleUnaryOperator
 */
@FunctionalInterface public interface OptionalDoubleUnaryOperator extends DoubleFunction<OptionalDouble> {
	/**
	 * Variation of {@link DoubleUnaryOperator#applyAsDouble(double)} that returns {@code OptionalDouble}.
	 * If this {@code OptionalDoubleUnaryOperator} is obtained from {@link ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code DoubleUnaryOperator} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code DoubleUnaryOperator}.
	 * 
	 * @param operand
	 *            see {@link DoubleUnaryOperator#apply(double)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link DoubleUnaryOperator#applyAsDouble(double)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)
	 * @see DoubleUnaryOperator#applyAsDouble(double)
	 */
	@Override OptionalDouble apply(double operand);
	/**
	 * Convert this {@code OptionalDoubleUnaryOperator} to plain {@code DoubleUnaryOperator} using default value.
	 * The returned {@code DoubleUnaryOperator} will unwrap present value from {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code DoubleUnaryOperator} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleUnaryOperator orElse(double result) {
		return new DefaultDoubleUnaryOperator(this, result);
	}
	/**
	 * Convert this {@code OptionalDoubleUnaryOperator} to plain {@code DoubleUnaryOperator} using fallback {@code DoubleSupplier}.
	 * The returned {@code DoubleUnaryOperator} will unwrap present value from {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code DoubleUnaryOperator} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleUnaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleUnaryOperator(this, source);
	}
}
