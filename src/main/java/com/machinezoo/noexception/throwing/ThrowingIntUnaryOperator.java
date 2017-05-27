// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntUnaryOperator} that allows throwing checked exceptions.
 * 
 * {@code ThrowingIntUnaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)
 * @see IntUnaryOperator
 */
@FunctionalInterface public interface ThrowingIntUnaryOperator {
	/**
	 * Variation of {@link IntUnaryOperator#applyAsInt(int)} that allows throwing checked exceptions.
	 * 
	 * @param operand
	 *            See {@link IntUnaryOperator#applyAsInt(int)}
	 * @return See {@link IntUnaryOperator#applyAsInt(int)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)
	 * @see IntUnaryOperator#applyAsInt(int)
	 */
	int applyAsInt(int operand) throws Exception;
}
