// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntBinaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingIntBinaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)
 * @see IntBinaryOperator
 */
@FunctionalInterface
public interface ThrowingIntBinaryOperator {
	/**
	 * Variation of {@link IntBinaryOperator#applyAsInt(int, int)} that allows throwing checked exceptions.
	 * 
	 * @param left
	 *            see {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @param right
	 *            see {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @return see {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntBinaryOperator(ThrowingIntBinaryOperator)
	 * @see IntBinaryOperator#applyAsInt(int, int)
	 */
	int applyAsInt(int left, int right) throws Throwable;
}
