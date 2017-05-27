// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntToLongFunction} that allows throwing checked exceptions.
 * {@code ThrowingIntToLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)
 * @see IntToLongFunction
 */
@FunctionalInterface public interface ThrowingIntToLongFunction {
	/**
	 * Variation of {@link IntToLongFunction#applyAsLong(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link IntToLongFunction#applyAsLong(int)}
	 * @return See {@link IntToLongFunction#applyAsLong(int)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)
	 * @see IntToLongFunction#applyAsLong(int)
	 */
	long applyAsLong(int value) throws Exception;
}
