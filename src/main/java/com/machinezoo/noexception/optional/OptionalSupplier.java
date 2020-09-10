// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Supplier} that returns {@link Optional} instead of the raw value.
 * {@code OptionalSupplier} is typically obtained from {@link ExceptionHandler#supplier(Supplier)},
 * in which case its return value is empty when the underlying {@link Supplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Supplier}
 * @see ExceptionHandler#supplier(Supplier)
 * @see Supplier
 */
@FunctionalInterface
public interface OptionalSupplier<T> extends Supplier<Optional<T>> {
	/**
	 * Variation of {@link Supplier#get()} that returns {@link Optional}.
	 * If this {@code OptionalSupplier} is obtained from {@link ExceptionHandler#supplier(Supplier)},
	 * the {@link Optional} will be empty only if the underlying {@link Supplier} throws.
	 * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link Supplier} (possibly {@code null}).
	 * 
	 * @return {@link Optional} typically wrapping return value of {@link Supplier#get()},
	 *         or an empty {@link Optional} (typically signifying an exception)
	 * @see ExceptionHandler#supplier(Supplier)
	 * @see Supplier#get()
	 */
	@Override
	Optional<T> get();
	/**
	 * Converts this {@code OptionalSupplier} to plain {@link Supplier} using default value.
	 * The returned {@link Supplier} will unwrap present value from the {@link Optional} if possible,
	 * or return {@code result} if the {@link Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link Optional}
	 * @return plain {@link Supplier} that either unwraps {@link Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default Supplier<T> orElse(T result) {
		return new DefaultSupplier<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalSupplier} to plain {@link Supplier} using fallback {@link Supplier}.
	 * The returned {@link Supplier} will unwrap present value from the {@link Optional} if possible,
	 * or fall back to calling {@code source} if the {@link Optional} is empty.
	 * 
	 * @param source
	 *            {@link Supplier} to query for fallback value when {@link Optional} is empty
	 * @return plain {@link Supplier} that either unwraps {@link Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default Supplier<T> orElseGet(Supplier<T> source) {
		return new FallbackSupplier<T>(this, source);
	}
}
