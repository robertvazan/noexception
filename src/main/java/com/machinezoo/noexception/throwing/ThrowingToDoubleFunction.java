// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleFunction} that allows throwing checked exceptions.
 * {@code ThrowingToDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToDoubleFunction(ThrowingToDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ToDoubleFunction}.
 * @see CheckedExceptionHandler#fromToDoubleFunction(ThrowingToDoubleFunction)
 * @see ToDoubleFunction
 */
@FunctionalInterface public interface ThrowingToDoubleFunction<T> {
	/**
	 * Variation of {@link ToDoubleFunction#applyAsDouble(Object)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link ToDoubleFunction#applyAsDouble(Object)}
	 * @return See {@link ToDoubleFunction#applyAsDouble(Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToDoubleFunction(ThrowingToDoubleFunction)
	 * @see ToDoubleFunction#applyAsDouble(Object)
	 */
	double applyAsDouble(T value) throws Exception;
}
