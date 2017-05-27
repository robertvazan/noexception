// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntSupplier} that allows throwing checked exceptions.
 * {@code ThrowingIntSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)
 * @see IntSupplier
 */
@FunctionalInterface public interface ThrowingIntSupplier {
	/**
	 * Variation of {@link IntSupplier#getAsInt()} that allows throwing checked exceptions.
	 * 
	 * @return See {@link IntSupplier#getAsInt()}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)
	 * @see IntSupplier#getAsInt()
	 */
	int getAsInt() throws Exception;
}
