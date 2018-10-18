// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleFunction} that allows throwing checked exceptions.
 * {@code ThrowingDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link DoubleFunction}
 * @see CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)
 * @see DoubleFunction
 */
@FunctionalInterface public interface ThrowingDoubleFunction<R> {
	/**
	 * Variation of {@link DoubleFunction#apply(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link DoubleFunction#apply(double)}
	 * @return see {@link DoubleFunction#apply(double)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)
	 * @see DoubleFunction#apply(double)
	 */
	R apply(double value) throws Exception;
}
