// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntBinaryOperator} that allows throwing checked exceptions.
 * 
 * {@code ThrowingIntBinaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)
 * @see IntBinaryOperator
 */
@FunctionalInterface public interface ThrowingIntBinaryOperator {
	/**
	 * Variation of {@link IntBinaryOperator#applyAsInt(int, int)} that allows throwing checked exceptions.
	 * 
	 * @param left,
	 *            See {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @param right
	 *            See {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @return See {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)
	 * @see IntBinaryOperator#applyAsInt(int, int)
	 */
	int applyAsInt(int left, int right) throws Exception;
}
