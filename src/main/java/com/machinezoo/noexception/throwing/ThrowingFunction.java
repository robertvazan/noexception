// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Function} that allows throwing checked exceptions.
 * 
 * {@code ThrowingFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#function(ThrowingFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link Function}.
 * @param <R>
 *            See {@link Function}.
 * @see CheckedExceptionHandler#function(ThrowingFunction)
 * @see Function
 */
@FunctionalInterface public interface ThrowingFunction<T, R> {
	/**
	 * Variation of {@link Function#apply(Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            See {@link Function#apply(Object)}
	 * @return See {@link Function#apply(Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#function(ThrowingFunction)
	 * @see Function#apply(Object)
	 */
	R apply(T t) throws Exception;
}
