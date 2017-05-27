// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongSupplier} that allows throwing checked exceptions.
 * {@code ThrowingLongSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongSupplier(ThrowingLongSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongSupplier(ThrowingLongSupplier)
 * @see LongSupplier
 */
@FunctionalInterface public interface ThrowingLongSupplier {
	/**
	 * Variation of {@link LongSupplier#getAsLong()} that allows throwing checked exceptions.
	 * 
	 * @return see {@link LongSupplier#getAsLong()}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongSupplier(ThrowingLongSupplier)
	 * @see LongSupplier#getAsLong()
	 */
	long getAsLong() throws Exception;
}
