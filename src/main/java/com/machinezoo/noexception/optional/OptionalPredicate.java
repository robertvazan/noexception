// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Predicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalPredicate} is typically obtained from {@link ExceptionHandler#predicate(Predicate)},
 * in which case its return value is empty when the underlying {@code Predicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Predicate}
 * @see ExceptionHandler#predicate(Predicate)
 * @see Predicate
 */
@FunctionalInterface public interface OptionalPredicate<T> {
	/**
	 * Variation of {@link Predicate#test(Object)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalPredicate} is obtained from {@link ExceptionHandler#predicate(Predicate)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code Predicate} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code Predicate}.
	 * 
	 * @param t
	 *            see {@link Predicate#test(Object)}
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link Predicate#test(Object)},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#predicate(Predicate)
	 * @see Predicate#test(Object)
	 */
	OptionalBoolean test(T t);
	/**
	 * Convert this {@code OptionalPredicate} to plain {@code Predicate} using default value.
	 * The returned {@code Predicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code Predicate} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default Predicate<T> orElse(boolean result) {
		return new DefaultPredicate<T>(this, result);
	}
	/**
	 * Convert this {@code OptionalPredicate} to plain {@code Predicate} using fallback {@code BooleanSupplier}.
	 * The returned {@code Predicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code Predicate} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default Predicate<T> orElseGet(BooleanSupplier source) {
		return new FallbackPredicate<T>(this, source);
	}
}
