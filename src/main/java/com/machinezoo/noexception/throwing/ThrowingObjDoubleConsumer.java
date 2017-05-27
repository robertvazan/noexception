// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ObjDoubleConsumer} that allows throwing checked exceptions.
 * 
 * {@code ThrowingObjDoubleConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromObjDoubleConsumer(ThrowingObjDoubleConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link ObjDoubleConsumer}.
 * @see CheckedExceptionHandler#fromObjDoubleConsumer(ThrowingObjDoubleConsumer)
 * @see ObjDoubleConsumer
 */
@FunctionalInterface public interface ThrowingObjDoubleConsumer<T> {
	/**
	 * Variation of {@link ObjDoubleConsumer#accept(Object, double)} that allows throwing checked exceptions.
	 * 
	 * @param t,
	 *            See {@link ObjDoubleConsumer#accept(Object, double)}
	 * @param value
	 *            See {@link ObjDoubleConsumer#accept(Object, double)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromObjDoubleConsumer(ThrowingObjDoubleConsumer)
	 * @see ObjDoubleConsumer#accept(Object, double)
	 */
	void accept(T t, double value) throws Exception;
}
