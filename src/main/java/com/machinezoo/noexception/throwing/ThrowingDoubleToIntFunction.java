// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToIntFunction} that allows throwing checked exceptions.
 * {@code ThrowingDoubleToIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleToIntFunction(ThrowingDoubleToIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleToIntFunction(ThrowingDoubleToIntFunction)
 * @see DoubleToIntFunction
 */
@FunctionalInterface public interface ThrowingDoubleToIntFunction {
	/**
	 * Variation of {@link DoubleToIntFunction#applyAsInt(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link DoubleToIntFunction#applyAsInt(double)}
	 * @return See {@link DoubleToIntFunction#applyAsInt(double)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromDoubleToIntFunction(ThrowingDoubleToIntFunction)
	 * @see DoubleToIntFunction#applyAsInt(double)
	 */
	int applyAsInt(double value) throws Exception;
}
