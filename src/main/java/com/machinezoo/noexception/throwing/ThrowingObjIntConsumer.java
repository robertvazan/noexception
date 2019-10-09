// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ObjIntConsumer} that allows throwing checked exceptions.
 * {@code ThrowingObjIntConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromObjIntConsumer(ThrowingObjIntConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ObjIntConsumer}
 * @see CheckedExceptionHandler#fromObjIntConsumer(ThrowingObjIntConsumer)
 * @see ObjIntConsumer
 */
@FunctionalInterface public interface ThrowingObjIntConsumer<T> {
	/**
	 * Variation of {@link ObjIntConsumer#accept(Object, int)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link ObjIntConsumer#accept(Object, int)}
	 * @param value
	 *            see {@link ObjIntConsumer#accept(Object, int)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromObjIntConsumer(ThrowingObjIntConsumer)
	 * @see ObjIntConsumer#accept(Object, int)
	 */
	void accept(T t, int value) throws Throwable;
}
