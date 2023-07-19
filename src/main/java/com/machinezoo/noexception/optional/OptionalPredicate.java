// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Predicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalPredicate} is typically obtained from {@link ExceptionHandler#predicate(Predicate)},
 * in which case its return value is empty when the underlying {@link Predicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Predicate}
 * @see ExceptionHandler#predicate(Predicate)
 * @see Predicate
 */
@FunctionalInterface
public interface OptionalPredicate<T> {
    /**
     * Variation of {@link Predicate#test(Object)} that returns {@link OptionalBoolean}.
     * If this {@code OptionalPredicate} is obtained from {@link ExceptionHandler#predicate(Predicate)},
     * the {@link OptionalBoolean} will be empty only if the underlying {@link Predicate} throws.
     * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link Predicate}.
     * 
     * @param t
     *            see {@link Predicate#test(Object)}
     * @return {@link OptionalBoolean} typically wrapping return value of {@link Predicate#test(Object)},
     *         or an empty {@link OptionalBoolean} (typically signifying an exception)
     * @see ExceptionHandler#predicate(Predicate)
     * @see Predicate#test(Object)
     */
    OptionalBoolean test(T t);
    /**
     * Converts this {@code OptionalPredicate} to plain {@link Predicate} using default value.
     * The returned {@link Predicate} will unwrap present value from the {@link OptionalBoolean} if possible,
     * or return {@code result} if the {@link OptionalBoolean} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalBoolean}
     * @return plain {@link Predicate} that either unwraps {@link OptionalBoolean} or returns default value
     * @see #orElseGet(BooleanSupplier)
     * @see OptionalBoolean#orElse(boolean)
     */
    default Predicate<T> orElse(boolean result) {
        return new DefaultPredicate<T>(this, result);
    }
    /**
     * Converts this {@code OptionalPredicate} to plain {@link Predicate} using fallback {@link BooleanSupplier}.
     * The returned {@link Predicate} will unwrap present value from the {@link OptionalBoolean} if possible,
     * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
     * 
     * @param source
     *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
     * @return plain {@link Predicate} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
     * @see #orElse(boolean)
     * @see OptionalBoolean#orElseGet(BooleanSupplier)
     */
    default Predicate<T> orElseGet(BooleanSupplier source) {
        return new FallbackPredicate<T>(this, source);
    }
}
