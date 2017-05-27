// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiFunction} that allows throwing checked exceptions.
 * {@code ThrowingBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link BiFunction}.
 * @param <U>
 *            See {@link BiFunction}.
 * @param <R>
 *            See {@link BiFunction}.
 * @see CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)
 * @see BiFunction
 */
@FunctionalInterface public interface ThrowingBiFunction<T, U, R> {
	/**
	 * Variation of {@link BiFunction#apply(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link BiFunction#apply(Object, Object)}
	 * @param u
	 *            See {@link BiFunction#apply(Object, Object)}
	 * @return See {@link BiFunction#apply(Object, Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)
	 * @see BiFunction#apply(Object, Object)
	 */
	R apply(T t, U u) throws Exception;
}
