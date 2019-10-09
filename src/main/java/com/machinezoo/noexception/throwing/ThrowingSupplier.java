// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Supplier} that allows throwing checked exceptions.
 * {@code ThrowingSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#supplier(ThrowingSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Supplier}
 * @see CheckedExceptionHandler#supplier(ThrowingSupplier)
 * @see Supplier
 */
@FunctionalInterface public interface ThrowingSupplier<T> {
	/**
	 * Variation of {@link Supplier#get()} that allows throwing checked exceptions.
	 * 
	 * @return see {@link Supplier#get()}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#supplier(ThrowingSupplier)
	 * @see Supplier#get()
	 */
	T get() throws Throwable;
}
