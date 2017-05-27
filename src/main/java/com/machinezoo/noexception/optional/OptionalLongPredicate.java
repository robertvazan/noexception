// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

/**
 * Variation of {@link LongPredicate} that returns {@code OptionalBoolean} instead of the raw value.
 * {@code OptionalLongPredicate} is typically obtained from {@link ExceptionHandler#fromLongPredicate(LongPredicate)},
 * in which case its return value is empty when the underlying {@code LongPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongPredicate(LongPredicate)
 * @see LongPredicate
 */
@FunctionalInterface public interface OptionalLongPredicate {
	/**
	 * Variation of {@link LongPredicate#test(long)} that returns {@code OptionalBoolean}.
	 * If this {@code OptionalLongPredicate} is obtained from {@link ExceptionHandler#fromLongPredicate(LongPredicate)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code LongPredicate} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code LongPredicate}.
	 * 
	 * @param value
	 *            see {@link LongPredicate#test(long)}
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link LongPredicate#test(long)},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongPredicate(LongPredicate)
	 * @see LongPredicate#test(long)
	 */
	OptionalBoolean test(long value);
	/**
	 * Convert this {@code OptionalLongPredicate} to plain {@code LongPredicate} using default value.
	 * The returned {@code LongPredicate} will unwrap present value from {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code LongPredicate} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default LongPredicate orElse(boolean result) {
		return new DefaultLongPredicate(this, result);
	}
	/**
	 * Convert this {@code OptionalLongPredicate} to plain {@code LongPredicate} using fallback {@code BooleanSupplier}.
	 * The returned {@code LongPredicate} will unwrap present value from {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code LongPredicate} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default LongPredicate orElseGet(BooleanSupplier source) {
		return new FallbackLongPredicate(this, source);
	}
}
