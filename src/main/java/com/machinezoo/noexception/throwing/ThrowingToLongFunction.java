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
 *            See {@link ToLongFunction}.
 * @see CheckedExceptionHandler#fromToLongFunction(ThrowingToLongFunction)
 * @see ToLongFunction
 */
@FunctionalInterface public interface ThrowingToLongFunction<T> {
	/**
	 * Variation of {@link ToLongFunction#applyAsLong(Object)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link ToLongFunction#applyAsLong(Object)}
	 * @return See {@link ToLongFunction#applyAsLong(Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToLongFunction(ThrowingToLongFunction)
	 * @see ToLongFunction#applyAsLong(Object)
	 */
	long applyAsLong(T value) throws Exception;
}
