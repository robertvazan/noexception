// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleBinaryOperator} that allows throwing checked exceptions.
 * 
 * {@code ThrowingDoubleBinaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator)
 * @see DoubleBinaryOperator
 */
@FunctionalInterface public interface ThrowingDoubleBinaryOperator {
	/**
	 * Variation of {@link DoubleBinaryOperator#applyAsDouble(double, double)} that allows throwing checked exceptions.
	 * 
	 * @param left,
	 *            See {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @param right
	 *            See {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @return See {@link DoubleBinaryOperator#applyAsDouble(double, double)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator)
	 * @see DoubleBinaryOperator#applyAsDouble(double, double)
	 */
	double applyAsDouble(double left, double right) throws Exception;
}
