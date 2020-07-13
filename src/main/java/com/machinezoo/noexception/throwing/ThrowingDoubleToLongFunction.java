// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToLongFunction} that allows throwing checked exceptions.
 * {@code ThrowingDoubleToLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleToLongFunction(ThrowingDoubleToLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleToLongFunction(ThrowingDoubleToLongFunction)
 * @see DoubleToLongFunction
 */
@FunctionalInterface
public interface ThrowingDoubleToLongFunction {
	/**
	 * Variation of {@link DoubleToLongFunction#applyAsLong(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link DoubleToLongFunction#applyAsLong(double)}
	 * @return see {@link DoubleToLongFunction#applyAsLong(double)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromDoubleToLongFunction(ThrowingDoubleToLongFunction)
	 * @see DoubleToLongFunction#applyAsLong(double)
	 */
	long applyAsLong(double value) throws Throwable;
}
