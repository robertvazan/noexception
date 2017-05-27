// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToIntFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingLongToIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)
 * @see LongToIntFunction
 */
@FunctionalInterface public interface ThrowingLongToIntFunction {
	/**
	 * Variation of {@link LongToIntFunction#applyAsInt(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link LongToIntFunction#applyAsInt(long)}
	 * @return See {@link LongToIntFunction#applyAsInt(long)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)
	 * @see LongToIntFunction#applyAsInt(long)
	 */
	int applyAsInt(long value) throws Exception;
}
