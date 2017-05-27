// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongFunction} that allows throwing checked exceptions.
 * {@code ThrowingToLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToLongFunction(ThrowingToLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongFunction}
 * @see CheckedExceptionHandler#fromToLongFunction(ThrowingToLongFunction)
 * @see ToLongFunction
 */
@FunctionalInterface public interface ThrowingToLongFunction<T> {
	/**
	 * Variation of {@link ToLongFunction#applyAsLong(Object)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link ToLongFunction#applyAsLong(Object)}
	 * @return see {@link ToLongFunction#applyAsLong(Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromToLongFunction(ThrowingToLongFunction)
	 * @see ToLongFunction#applyAsLong(Object)
	 */
	long applyAsLong(T value) throws Exception;
}
