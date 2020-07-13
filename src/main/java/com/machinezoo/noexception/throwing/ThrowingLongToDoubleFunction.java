// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToDoubleFunction} that allows throwing checked exceptions.
 * {@code ThrowingLongToDoubleFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)
 * @see LongToDoubleFunction
 */
@FunctionalInterface
public interface ThrowingLongToDoubleFunction {
	/**
	 * Variation of {@link LongToDoubleFunction#applyAsDouble(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link LongToDoubleFunction#applyAsDouble(long)}
	 * @return see {@link LongToDoubleFunction#applyAsDouble(long)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongToDoubleFunction(ThrowingLongToDoubleFunction)
	 * @see LongToDoubleFunction#applyAsDouble(long)
	 */
	double applyAsDouble(long value) throws Throwable;
}
