// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoublePredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalDoublePredicate} is typically obtained from {@link ExceptionHandler#fromDoublePredicate(DoublePredicate)},
 * in which case its return value is empty when the underlying {@code DoublePredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoublePredicate(DoublePredicate)
 * @see DoublePredicate
 */
@FunctionalInterface public interface OptionalDoublePredicate {
	/**
	 * Variation of {@link DoublePredicate#test(double)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalDoublePredicate} is obtained from {@link ExceptionHandler#fromDoublePredicate(DoublePredicate)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code DoublePredicate} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code DoublePredicate}.
	 * 
	 * @param value
	 *            see {@link DoublePredicate#test(double)}
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link DoublePredicate#test(double)},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoublePredicate(DoublePredicate)
	 * @see DoublePredicate#test(double)
	 */
	OptionalBoolean test(double value);
	/**
	 * Convert this {@code OptionalDoublePredicate} to plain {@code DoublePredicate} using default value.
	 * The returned {@code DoublePredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code DoublePredicate} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default DoublePredicate orElse(boolean result) {
		return new DefaultDoublePredicate(this, result);
	}
	/**
	 * Convert this {@code OptionalDoublePredicate} to plain {@code DoublePredicate} using fallback {@code BooleanSupplier}.
	 * The returned {@code DoublePredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code DoublePredicate} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default DoublePredicate orElseGet(BooleanSupplier source) {
		return new FallbackDoublePredicate(this, source);
	}
}
