// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleUnaryOperator} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleUnaryOperator} is typically obtained from {@link ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)},
 * in which case its return value is empty when the underlying {@link DoubleUnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)
 * @see DoubleUnaryOperator
 */
@FunctionalInterface
public interface OptionalDoubleUnaryOperator extends DoubleFunction<OptionalDouble> {
	/**
	 * Variation of {@link DoubleUnaryOperator#applyAsDouble(double)} that returns {@link OptionalDouble}.
	 * If this {@code OptionalDoubleUnaryOperator} is obtained from {@link ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)},
	 * the {@link OptionalDouble} will be empty only if the underlying {@link DoubleUnaryOperator} throws.
	 * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link DoubleUnaryOperator}.
	 * 
	 * @param operand
	 *            see {@link DoubleUnaryOperator#applyAsDouble(double)}
 * @return {@link OptionalDouble} typically wrapping return value of {@link DoubleUnaryOperator#applyAsDouble(double)},
 *         or an empty {@link OptionalDouble} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleUnaryOperator(DoubleUnaryOperator)
 * @see DoubleUnaryOperator#applyAsDouble(double)
 */
	@Override
	OptionalDouble apply(double operand);
	/**
	 * Converts this {@code OptionalDoubleUnaryOperator} to plain {@link DoubleUnaryOperator} using default value.
	 * The returned {@link DoubleUnaryOperator} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or return {@code result} if the {@link OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalDouble}
	 * @return plain {@link DoubleUnaryOperator} that either unwraps {@link OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleUnaryOperator orElse(double result) {
		return new DefaultDoubleUnaryOperator(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleUnaryOperator} to plain {@link DoubleUnaryOperator} using fallback {@link DoubleSupplier}.
	 * The returned {@link DoubleUnaryOperator} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
	 * @return plain {@link DoubleUnaryOperator} that either unwraps {@link OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleUnaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleUnaryOperator(this, source);
	}
}
