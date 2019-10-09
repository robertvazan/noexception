// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleConsumer} that allows throwing checked exceptions.
 * {@code ThrowingDoubleConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleConsumer(ThrowingDoubleConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleConsumer(ThrowingDoubleConsumer)
 * @see DoubleConsumer
 */
@FunctionalInterface public interface ThrowingDoubleConsumer {
	/**
	 * Variation of {@link DoubleConsumer#accept(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link DoubleConsumer#accept(double)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromDoubleConsumer(ThrowingDoubleConsumer)
	 * @see DoubleConsumer#accept(double)
	 */
	void accept(double value) throws Throwable;
}
