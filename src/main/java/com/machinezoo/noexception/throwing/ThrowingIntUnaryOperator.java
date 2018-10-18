// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntUnaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingIntUnaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)
 * @see IntUnaryOperator
 */
@FunctionalInterface public interface ThrowingIntUnaryOperator {
	/**
	 * Variation of {@link IntUnaryOperator#applyAsInt(int)} that allows throwing checked exceptions.
	 * 
	 * @param operand
	 *            see {@link IntUnaryOperator#applyAsInt(int)}
	 * @return see {@link IntUnaryOperator#applyAsInt(int)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntUnaryOperator(ThrowingIntUnaryOperator)
	 * @see IntUnaryOperator#applyAsInt(int)
	 */
	int applyAsInt(int operand) throws Exception;
}
