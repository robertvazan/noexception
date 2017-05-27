// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntBiFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingToIntBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ToIntBiFunction}.
 * @param <U>
 *            See {@link ToIntBiFunction}.
 * @see CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)
 * @see ToIntBiFunction
 */
@FunctionalInterface public interface ThrowingToIntBiFunction<T, U> {
	/**
	 * Variation of {@link ToIntBiFunction#applyAsInt(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @param u
	 *            See {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @return See {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)
	 * @see ToIntBiFunction#applyAsInt(Object, Object)
	 */
	int applyAsInt(T t, U u) throws Exception;
}
