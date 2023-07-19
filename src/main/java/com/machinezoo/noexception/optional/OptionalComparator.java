// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Comparator} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalComparator} is typically obtained from {@link ExceptionHandler#comparator(Comparator)},
 * in which case its return value is empty when the underlying {@link Comparator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Comparator}
 * @see ExceptionHandler#comparator(Comparator)
 * @see Comparator
 */
@FunctionalInterface
public interface OptionalComparator<T> {
    /**
     * Variation of {@link Comparator#compare(Object, Object)} that returns {@link OptionalInt}.
     * If this {@code OptionalComparator} is obtained from {@link ExceptionHandler#comparator(Comparator)},
     * the {@link OptionalInt} will be empty only if the underlying {@link Comparator} throws.
     * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link Comparator}.
     * 
     * @param left
     *            see {@link Comparator#compare(Object, Object)}
     * @param right
     *            see {@link Comparator#compare(Object, Object)}
     * @return {@link OptionalInt} typically wrapping return value of {@link Comparator#compare(Object, Object)},
     *         or an empty {@link OptionalInt} (typically signifying an exception)
     * @see ExceptionHandler#comparator(Comparator)
     * @see Comparator#compare(Object, Object)
     */
    OptionalInt compare(T left, T right);
    /**
     * Converts this {@code OptionalComparator} to plain {@link Comparator} using default value.
     * The returned {@link Comparator} will unwrap present value from the {@link OptionalInt} if possible,
     * or return {@code result} if the {@link OptionalInt} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalInt}
     * @return plain {@link Comparator} that either unwraps {@link OptionalInt} or returns default value
     * @see #orElseGet(IntSupplier)
     * @see OptionalInt#orElse(int)
     */
    default Comparator<T> orElse(int result) {
        return new DefaultComparator<T>(this, result);
    }
    /**
     * Converts this {@code OptionalComparator} to plain {@link Comparator} using fallback {@link IntSupplier}.
     * The returned {@link Comparator} will unwrap present value from the {@link OptionalInt} if possible,
     * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
     * 
     * @param source
     *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
     * @return plain {@link Comparator} that either unwraps {@link OptionalInt} or falls back to {@code source}
     * @see #orElse(int)
     * @see OptionalInt#orElseGet(IntSupplier)
     */
    default Comparator<T> orElseGet(IntSupplier source) {
        return new FallbackComparator<T>(this, source);
    }
}
