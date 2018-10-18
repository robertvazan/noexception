// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToLongBiFunction} that allows throwing checked exceptions.
 * {@code ThrowingToLongBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToLongBiFunction}
 * @param <U>
 *            see {@link ToLongBiFunction}
 * @see CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)
 * @see ToLongBiFunction
 */
@FunctionalInterface public interface ThrowingToLongBiFunction<T, U> {
	/**
	 * Variation of {@link ToLongBiFunction#applyAsLong(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @param u
	 *            see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @return see {@link ToLongBiFunction#applyAsLong(Object, Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromToLongBiFunction(ThrowingToLongBiFunction)
	 * @see ToLongBiFunction#applyAsLong(Object, Object)
	 */
	long applyAsLong(T t, U u) throws Exception;
}
