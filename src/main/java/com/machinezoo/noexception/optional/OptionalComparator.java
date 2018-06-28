// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Comparator} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalComparator} is typically obtained from {@link ExceptionHandler#comparator(Comparator)},
 * in which case its return value is empty when the underlying {@code Comparator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Comparator}
 * @see ExceptionHandler#comparator(Comparator)
 * @see Comparator
 */
@FunctionalInterface public interface OptionalComparator<T> {
	/**
	 * Variation of {@link Comparator#compare(Object, Object)} that returns {@link OptionalInt}.
	 * If this {@code OptionalComparator} is obtained from {@link ExceptionHandler#comparator(Comparator)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code Comparator} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code Comparator}.
	 * 
	 * @param left
	 *            see {@link Comparator#compare(Object, Object)}
	 * @param right
	 *            see {@link Comparator#compare(Object, Object)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link Comparator#compare(Object, Object)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#comparator(Comparator)
	 * @see Comparator#compare(Object, Object)
	 */
	OptionalInt compare(T left, T right);
	/**
	 * Convert this {@code OptionalComparator} to plain {@code Comparator} using default value.
	 * The returned {@code Comparator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code Comparator} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default Comparator<T> orElse(int result) {
		return new DefaultComparator<T>(this, result);
	}
	/**
	 * Convert this {@code OptionalComparator} to plain {@code Comparator} using fallback {@code IntSupplier}.
	 * The returned {@code Comparator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code Comparator} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default Comparator<T> orElseGet(IntSupplier source) {
		return new FallbackComparator<T>(this, source);
	}
}
