// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiConsumer} that allows throwing checked exceptions.
 * {@code ThrowingBiConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiConsumer}
 * @param <U>
 *            see {@link BiConsumer}
 * @see CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)
 * @see BiConsumer
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T, U> {
	/**
	 * Variation of {@link BiConsumer#accept(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link BiConsumer#accept(Object, Object)}
	 * @param u
	 *            see {@link BiConsumer#accept(Object, Object)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromBiConsumer(ThrowingBiConsumer)
	 * @see BiConsumer#accept(Object, Object)
	 */
	void accept(T t, U u) throws Throwable;
}
