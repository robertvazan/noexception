// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiConsumer} that allows throwing checked exceptions.
 * 
 * {@code ThrowingBiConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link BiConsumer}.
 * @param <U>
 *            See {@link BiConsumer}.
 * @see CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)
 * @see BiConsumer
 */
@FunctionalInterface public interface ThrowingBiConsumer<T, U> {
	/**
	 * Variation of {@link BiConsumer#accept(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link BiConsumer#accept(Object, Object)}
	 * @param u
	 *            See {@link BiConsumer#accept(Object, Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)
	 * @see BiConsumer#accept(Object, Object)
	 */
	void accept(T t, U u) throws Exception;
}
