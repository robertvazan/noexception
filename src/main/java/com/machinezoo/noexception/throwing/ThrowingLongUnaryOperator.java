// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongUnaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingLongUnaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongUnaryOperator(ThrowingLongUnaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongUnaryOperator(ThrowingLongUnaryOperator)
 * @see LongUnaryOperator
 */
@FunctionalInterface
public interface ThrowingLongUnaryOperator {
	/**
	 * Variation of {@link LongUnaryOperator#applyAsLong(long)} that allows throwing checked exceptions.
	 * 
	 * @param operand
	 *            see {@link LongUnaryOperator#applyAsLong(long)}
	 * @return see {@link LongUnaryOperator#applyAsLong(long)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongUnaryOperator(ThrowingLongUnaryOperator)
	 * @see LongUnaryOperator#applyAsLong(long)
	 */
	long applyAsLong(long operand) throws Throwable;
}
