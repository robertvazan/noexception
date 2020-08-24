// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiPredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalBiPredicate} is typically obtained from {@link ExceptionHandler#fromBiPredicate(BiPredicate)},
 * in which case its return value is empty when the underlying {@link BiPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiPredicate}
 * @param <U>
 *            see {@link BiPredicate}
 * @see ExceptionHandler#fromBiPredicate(BiPredicate)
 * @see BiPredicate
 */
@FunctionalInterface
public interface OptionalBiPredicate<T, U> {
	/**
	 * Variation of {@link BiPredicate#test(Object, Object)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalBiPredicate} is obtained from {@link ExceptionHandler#fromBiPredicate(BiPredicate)},
	 * the {@link OptionalBoolean} will be empty only if the underlying {@link BiPredicate} throws.
	 * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link BiPredicate}.
	 * 
	 * @param t
	 *            see {@link BiPredicate#test(Object, Object)}
	 * @param u
	 *            see {@link BiPredicate#test(Object, Object)}
 * @return {@link OptionalBoolean} typically wrapping return value of {@link BiPredicate#test(Object, Object)},
 *         or an empty {@link OptionalBoolean} (typically signifying an exception)
 * @see ExceptionHandler#fromBiPredicate(BiPredicate)
 * @see BiPredicate#test(Object, Object)
 */
	OptionalBoolean test(T t, U u);
	/**
	 * Converts this {@code OptionalBiPredicate} to plain {@link BiPredicate} using default value.
	 * The returned {@link BiPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or return {@code result} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalBoolean}
	 * @return plain {@link BiPredicate} that either unwraps {@link OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default BiPredicate<T, U> orElse(boolean result) {
		return new DefaultBiPredicate<T, U>(this, result);
	}
	/**
	 * Converts this {@code OptionalBiPredicate} to plain {@link BiPredicate} using fallback {@link BooleanSupplier}.
	 * The returned {@link BiPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
	 * @return plain {@link BiPredicate} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default BiPredicate<T, U> orElseGet(BooleanSupplier source) {
		return new FallbackBiPredicate<T, U>(this, source);
	}
}
