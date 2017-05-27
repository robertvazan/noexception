// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <R>
 *            See {@link DoubleFunction}.
 * @see CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)
 * @see DoubleFunction
 */
@FunctionalInterface public interface ThrowingDoubleFunction<R> {
	/**
	 * Variation of {@link DoubleFunction#apply(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link DoubleFunction#apply(double)}
	 * @return See {@link DoubleFunction#apply(double)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromDoubleFunction(ThrowingDoubleFunction)
	 * @see DoubleFunction#apply(double)
	 */
	R apply(double value) throws Exception;
}
