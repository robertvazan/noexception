// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleSupplier} that allows throwing checked exceptions.
 * {@code ThrowingDoubleSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleSupplier(ThrowingDoubleSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleSupplier(ThrowingDoubleSupplier)
 * @see DoubleSupplier
 */
@FunctionalInterface
public interface ThrowingDoubleSupplier {
	/**
	 * Variation of {@link DoubleSupplier#getAsDouble()} that allows throwing checked exceptions.
	 * 
	 * @return see {@link DoubleSupplier#getAsDouble()}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromDoubleSupplier(ThrowingDoubleSupplier)
	 * @see DoubleSupplier#getAsDouble()
	 */
	double getAsDouble() throws Throwable;
}
