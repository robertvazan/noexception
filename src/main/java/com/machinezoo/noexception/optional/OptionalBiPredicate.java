// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiPredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalBiPredicate} is typically obtained from {@link ExceptionHandler#fromBiPredicate(BiPredicate)},
 * in which case its return value is empty when the underlying {@code BiPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiPredicate}
 * @param <U>
 *            see {@link BiPredicate}
 * @see ExceptionHandler#fromBiPredicate(BiPredicate)
 * @see BiPredicate
 */
@FunctionalInterface public interface OptionalBiPredicate<T, U> {
	/**
	 * Variation of {@link BiPredicate#test(Object, Object)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalBiPredicate} is obtained from {@link ExceptionHandler#fromBiPredicate(BiPredicate)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code BiPredicate} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code BiPredicate}.
	 * 
	 * @param t
	 *            see {@link BiPredicate#test(Object, Object)}
	 * @param u
	 *            see {@link BiPredicate#test(Object, Object)}
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link BiPredicate#test(Object, Object)},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromBiPredicate(BiPredicate)
	 * @see BiPredicate#test(Object, Object)
	 */
	OptionalBoolean test(T t, U u);
	/**
	 * Convert this {@code OptionalBiPredicate} to plain {@code BiPredicate} using default value.
	 * The returned {@code BiPredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code BiPredicate} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default BiPredicate<T, U> orElse(boolean result) {
		return new DefaultBiPredicate<T, U>(this, result);
	}
	/**
	 * Convert this {@code OptionalBiPredicate} to plain {@code BiPredicate} using fallback {@code BooleanSupplier}.
	 * The returned {@code BiPredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code BiPredicate} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default BiPredicate<T, U> orElseGet(BooleanSupplier source) {
		return new FallbackBiPredicate<T, U>(this, source);
	}
}
