// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntFunction} that allows throwing checked exceptions.
 * {@code ThrowingToIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntFunction}
 * @see CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)
 * @see ToIntFunction
 */
@FunctionalInterface public interface ThrowingToIntFunction<T> {
	/**
	 * Variation of {@link ToIntFunction#applyAsInt(Object)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link ToIntFunction#applyAsInt(Object)}
	 * @return see {@link ToIntFunction#applyAsInt(Object)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromToIntFunction(ThrowingToIntFunction)
	 * @see ToIntFunction#applyAsInt(Object)
	 */
	int applyAsInt(T value) throws Throwable;
}
