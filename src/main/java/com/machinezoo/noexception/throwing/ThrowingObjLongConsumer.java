// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ObjLongConsumer} that allows throwing checked exceptions.
 * {@code ThrowingObjLongConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromObjLongConsumer(ThrowingObjLongConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ObjLongConsumer}
 * @see CheckedExceptionHandler#fromObjLongConsumer(ThrowingObjLongConsumer)
 * @see ObjLongConsumer
 */
@FunctionalInterface public interface ThrowingObjLongConsumer<T> {
	/**
	 * Variation of {@link ObjLongConsumer#accept(Object, long)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link ObjLongConsumer#accept(Object, long)}
	 * @param value
	 *            see {@link ObjLongConsumer#accept(Object, long)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromObjLongConsumer(ThrowingObjLongConsumer)
	 * @see ObjLongConsumer#accept(Object, long)
	 */
	void accept(T t, long value) throws Exception;
}
