// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Supplier} that allows throwing checked exceptions.
 * {@code ThrowingSupplier} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#supplier(ThrowingSupplier)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link Supplier}.
 * @see CheckedExceptionHandler#supplier(ThrowingSupplier)
 * @see Supplier
 */
@FunctionalInterface public interface ThrowingSupplier<T> {
	/**
	 * Variation of {@link Supplier#get()} that allows throwing checked exceptions.
	 * 
	 * @return See {@link Supplier#get()}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#supplier(ThrowingSupplier)
	 * @see Supplier#get()
	 */
	T get() throws Exception;
}
