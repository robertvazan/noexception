// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntToLongFunction} that allows throwing checked exceptions.
 * {@code ThrowingIntToLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)
 * @see IntToLongFunction
 */
@FunctionalInterface public interface ThrowingIntToLongFunction {
	/**
	 * Variation of {@link IntToLongFunction#applyAsLong(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link IntToLongFunction#applyAsLong(int)}
	 * @return see {@link IntToLongFunction#applyAsLong(int)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntToLongFunction(ThrowingIntToLongFunction)
	 * @see IntToLongFunction#applyAsLong(int)
	 */
	long applyAsLong(int value) throws Exception;
}
