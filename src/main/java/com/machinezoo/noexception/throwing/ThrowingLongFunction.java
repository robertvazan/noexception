// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongFunction} that allows throwing checked exceptions.
 * 
 * {@code ThrowingLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <R>
 *            See {@link LongFunction}.
 * @see CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)
 * @see LongFunction
 */
@FunctionalInterface public interface ThrowingLongFunction<R> {
	/**
	 * Variation of {@link LongFunction#apply(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link LongFunction#apply(long)}
	 * @return See {@link LongFunction#apply(long)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)
	 * @see LongFunction#apply(long)
	 */
	R apply(long value) throws Exception;
}
