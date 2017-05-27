// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingToIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ToIntFunction}.
 * @see CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)
 * @see ToIntFunction
 */
@FunctionalInterface public interface ThrowingToIntFunction<T> {
	/**
	 * Variation of {@link ToIntFunction#applyAsInt(Object)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link ToIntFunction#applyAsInt(Object)}
	 * @return See {@link ToIntFunction#applyAsInt(Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)
	 * @see ToIntFunction#applyAsInt(Object)
	 */
	int applyAsInt(T value) throws Exception;
}
