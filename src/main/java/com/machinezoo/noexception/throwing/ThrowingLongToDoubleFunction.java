// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToDoubleFunction} that allows throwing checked exceptions.
 * {@code ThrowingLongToDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)
 * @see LongToDoubleFunction
 */
@FunctionalInterface public interface ThrowingLongToDoubleFunction {
	/**
	 * Variation of {@link LongToDoubleFunction#applyAsDouble(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link LongToDoubleFunction#applyAsDouble(long)}
	 * @return See {@link LongToDoubleFunction#applyAsDouble(long)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)
	 * @see LongToDoubleFunction#applyAsDouble(long)
	 */
	double applyAsDouble(long value) throws Exception;
}
