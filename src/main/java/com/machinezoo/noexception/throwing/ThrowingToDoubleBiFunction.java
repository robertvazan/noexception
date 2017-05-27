// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleBiFunction} that allows throwing checked exceptions.
 * {@code ThrowingToDoubleBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ToDoubleBiFunction}.
 * @param <U>
 *            See {@link ToDoubleBiFunction}.
 * @see CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)
 * @see ToDoubleBiFunction
 */
@FunctionalInterface public interface ThrowingToDoubleBiFunction<T, U> {
	/**
	 * Variation of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @param u
	 *            See {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @return See {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)
	 * @see ToDoubleBiFunction#applyAsDouble(Object, Object)
	 */
	double applyAsDouble(T t, U u) throws Exception;
}
