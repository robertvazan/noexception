// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntToDoubleFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingIntToDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntToDoubleFunction(ThrowingIntToDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntToDoubleFunction(ThrowingIntToDoubleFunction)
 * @see IntToDoubleFunction
 */
@FunctionalInterface public interface ThrowingIntToDoubleFunction {
	/**
	 * Variation of {@link IntToDoubleFunction#applyAsDouble(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link IntToDoubleFunction#applyAsDouble(int)}
	 * @return See {@link IntToDoubleFunction#applyAsDouble(int)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntToDoubleFunction(ThrowingIntToDoubleFunction)
	 * @see IntToDoubleFunction#applyAsDouble(int)
	 */
	double applyAsDouble(int value) throws Exception;
}
