// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongBinaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingLongBinaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongBinaryOperator(ThrowingLongBinaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongBinaryOperator(ThrowingLongBinaryOperator)
 * @see LongBinaryOperator
 */
@FunctionalInterface public interface ThrowingLongBinaryOperator {
	/**
	 * Variation of {@link LongBinaryOperator#applyAsLong(long, long)} that allows throwing checked exceptions.
	 * 
	 * @param left
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @param right
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @return see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongBinaryOperator(ThrowingLongBinaryOperator)
	 * @see LongBinaryOperator#applyAsLong(long, long)
	 */
	long applyAsLong(long left, long right) throws Exception;
}
