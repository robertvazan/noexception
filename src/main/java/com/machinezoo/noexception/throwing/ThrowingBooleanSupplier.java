// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BooleanSupplier} that allows throwing checked exceptions.
 * {@code ThrowingBooleanSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBooleanSupplier(ThrowingBooleanSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromBooleanSupplier(ThrowingBooleanSupplier)
 * @see BooleanSupplier
 */
@FunctionalInterface public interface ThrowingBooleanSupplier {
	/**
	 * Variation of {@link BooleanSupplier#getAsBoolean()} that allows throwing checked exceptions.
	 * 
	 * @return see {@link BooleanSupplier#getAsBoolean()}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromBooleanSupplier(ThrowingBooleanSupplier)
	 * @see BooleanSupplier#getAsBoolean()
	 */
	boolean getAsBoolean() throws Exception;
}
