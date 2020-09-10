// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoublePredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalDoublePredicate} is typically obtained from {@link ExceptionHandler#fromDoublePredicate(DoublePredicate)},
 * in which case its return value is empty when the underlying {@link DoublePredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoublePredicate(DoublePredicate)
 * @see DoublePredicate
 */
@FunctionalInterface
public interface OptionalDoublePredicate {
	/**
	 * Variation of {@link DoublePredicate#test(double)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalDoublePredicate} is obtained from {@link ExceptionHandler#fromDoublePredicate(DoublePredicate)},
	 * the {@link OptionalBoolean} will be empty only if the underlying {@link DoublePredicate} throws.
	 * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link DoublePredicate}.
	 * 
	 * @param value
	 *            see {@link DoublePredicate#test(double)}
	 * @return {@link OptionalBoolean} typically wrapping return value of {@link DoublePredicate#test(double)},
	 *         or an empty {@link OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoublePredicate(DoublePredicate)
	 * @see DoublePredicate#test(double)
	 */
	OptionalBoolean test(double value);
	/**
	 * Converts this {@code OptionalDoublePredicate} to plain {@link DoublePredicate} using default value.
	 * The returned {@link DoublePredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or return {@code result} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalBoolean}
	 * @return plain {@link DoublePredicate} that either unwraps {@link OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default DoublePredicate orElse(boolean result) {
		return new DefaultDoublePredicate(this, result);
	}
	/**
	 * Converts this {@code OptionalDoublePredicate} to plain {@link DoublePredicate} using fallback {@link BooleanSupplier}.
	 * The returned {@link DoublePredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
	 * @return plain {@link DoublePredicate} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default DoublePredicate orElseGet(BooleanSupplier source) {
		return new FallbackDoublePredicate(this, source);
	}
}
