// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongBiFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingToLongBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ToLongBiFunction}.
 * @param <U>
 *            See {@link ToLongBiFunction}.
 * @see CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)
 * @see ToLongBiFunction
 */
@FunctionalInterface public interface ThrowingToLongBiFunction<T, U> {
	/**
	 * Variation of {@link ToLongBiFunction#applyAsLong(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @param u
	 *            See {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @return See {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)
	 * @see ToLongBiFunction#applyAsLong(Object, Object)
	 */
	long applyAsLong(T t, U u) throws Exception;
}
