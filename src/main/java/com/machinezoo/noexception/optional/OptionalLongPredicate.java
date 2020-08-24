// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongPredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalLongPredicate} is typically obtained from {@link ExceptionHandler#fromLongPredicate(LongPredicate)},
 * in which case its return value is empty when the underlying {@link LongPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongPredicate(LongPredicate)
 * @see LongPredicate
 */
@FunctionalInterface
public interface OptionalLongPredicate {
	/**
	 * Variation of {@link LongPredicate#test(long)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalLongPredicate} is obtained from {@link ExceptionHandler#fromLongPredicate(LongPredicate)},
	 * the {@link OptionalBoolean} will be empty only if the underlying {@link LongPredicate} throws.
	 * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link LongPredicate}.
	 * 
	 * @param value
	 *            see {@link LongPredicate#test(long)}
 * @return {@link OptionalBoolean} typically wrapping return value of {@link LongPredicate#test(long)},
 *         or an empty {@link OptionalBoolean} (typically signifying an exception)
 * @see ExceptionHandler#fromLongPredicate(LongPredicate)
 * @see LongPredicate#test(long)
 */
	OptionalBoolean test(long value);
	/**
	 * Converts this {@code OptionalLongPredicate} to plain {@link LongPredicate} using default value.
	 * The returned {@link LongPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or return {@code result} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalBoolean}
	 * @return plain {@link LongPredicate} that either unwraps {@link OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default LongPredicate orElse(boolean result) {
		return new DefaultLongPredicate(this, result);
	}
	/**
	 * Converts this {@code OptionalLongPredicate} to plain {@link LongPredicate} using fallback {@link BooleanSupplier}.
	 * The returned {@link LongPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
	 * @return plain {@link LongPredicate} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default LongPredicate orElseGet(BooleanSupplier source) {
		return new FallbackLongPredicate(this, source);
	}
}
