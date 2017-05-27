// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntConsumer} that allows throwing checked exceptions.
 * {@code ThrowingIntConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntConsumer(ThrowingIntConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntConsumer(ThrowingIntConsumer)
 * @see IntConsumer
 */
@FunctionalInterface public interface ThrowingIntConsumer {
	/**
	 * Variation of {@link IntConsumer#accept(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link IntConsumer#accept(int)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntConsumer(ThrowingIntConsumer)
	 * @see IntConsumer#accept(int)
	 */
	void accept(int value) throws Exception;
}
