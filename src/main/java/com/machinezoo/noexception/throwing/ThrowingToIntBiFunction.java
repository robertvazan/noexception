// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntBiFunction} that allows throwing checked exceptions.
 * {@code ThrowingToIntBiFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntBiFunction}
 * @param <U>
 *            see {@link ToIntBiFunction}
 * @see CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)
 * @see ToIntBiFunction
 */
@FunctionalInterface public interface ThrowingToIntBiFunction<T, U> {
	/**
	 * Variation of {@link ToIntBiFunction#applyAsInt(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @param u
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @return see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromToIntBiFunction(ThrowingToIntBiFunction)
	 * @see ToIntBiFunction#applyAsInt(Object, Object)
	 */
	int applyAsInt(T t, U u) throws Exception;
}
