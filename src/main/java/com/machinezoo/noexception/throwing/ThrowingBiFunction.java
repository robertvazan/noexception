// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiFunction} that allows throwing checked exceptions.
 * {@code ThrowingBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiFunction}
 * @param <U>
 *            see {@link BiFunction}
 * @param <R>
 *            see {@link BiFunction}
 * @see CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)
 * @see BiFunction
 */
@FunctionalInterface public interface ThrowingBiFunction<T, U, R> {
	/**
	 * Variation of {@link BiFunction#apply(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link BiFunction#apply(Object, Object)}
	 * @param u
	 *            see {@link BiFunction#apply(Object, Object)}
	 * @return see {@link BiFunction#apply(Object, Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromBiFunction(ThrowingBiFunction)
	 * @see BiFunction#apply(Object, Object)
	 */
	R apply(T t, U u) throws Exception;
}
