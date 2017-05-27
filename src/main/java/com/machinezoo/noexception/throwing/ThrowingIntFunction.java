// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntFunction} that allows throwing checked exceptions.
 * {@code ThrowingIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntFunction(ThrowingIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <R>
 *            see {@link IntFunction}
 * @see CheckedExceptionHandler#fromIntFunction(ThrowingIntFunction)
 * @see IntFunction
 */
@FunctionalInterface public interface ThrowingIntFunction<R> {
	/**
	 * Variation of {@link IntFunction#apply(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link IntFunction#apply(int)}
	 * @return see {@link IntFunction#apply(int)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntFunction(ThrowingIntFunction)
	 * @see IntFunction#apply(int)
	 */
	R apply(int value) throws Exception;
}
