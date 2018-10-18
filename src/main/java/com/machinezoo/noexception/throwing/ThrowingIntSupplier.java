// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntSupplier} that allows throwing checked exceptions.
 * {@code ThrowingIntSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)
 * @see IntSupplier
 */
@FunctionalInterface public interface ThrowingIntSupplier {
	/**
	 * Variation of {@link IntSupplier#getAsInt()} that allows throwing checked exceptions.
	 * 
	 * @return see {@link IntSupplier#getAsInt()}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromIntSupplier(ThrowingIntSupplier)
	 * @see IntSupplier#getAsInt()
	 */
	int getAsInt() throws Exception;
}
