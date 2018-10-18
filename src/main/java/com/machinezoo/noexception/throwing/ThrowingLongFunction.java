// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongFunction} that allows throwing checked exceptions.
 * {@code ThrowingLongFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link LongFunction}
 * @see CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)
 * @see LongFunction
 */
@FunctionalInterface public interface ThrowingLongFunction<R> {
	/**
	 * Variation of {@link LongFunction#apply(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link LongFunction#apply(long)}
	 * @return see {@link LongFunction#apply(long)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongFunction(ThrowingLongFunction)
	 * @see LongFunction#apply(long)
	 */
	R apply(long value) throws Exception;
}
