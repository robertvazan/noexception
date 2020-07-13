// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleBiFunction} that allows throwing checked exceptions.
 * {@code ThrowingToDoubleBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToDoubleBiFunction}
 * @param <U>
 *            see {@link ToDoubleBiFunction}
 * @see CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)
 * @see ToDoubleBiFunction
 */
@FunctionalInterface
public interface ThrowingToDoubleBiFunction<T, U> {
	/**
	 * Variation of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @param u
	 *            see {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @return see {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromToDoubleBiFunction(ThrowingToDoubleBiFunction)
	 * @see ToDoubleBiFunction#applyAsDouble(Object, Object)
	 */
	double applyAsDouble(T t, U u) throws Throwable;
}
