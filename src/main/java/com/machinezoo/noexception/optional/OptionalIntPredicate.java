// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntPredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalIntPredicate} is typically obtained from {@link ExceptionHandler#fromIntPredicate(IntPredicate)},
 * in which case its return value is empty when the underlying {@code IntPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntPredicate(IntPredicate)
 * @see IntPredicate
 */
@FunctionalInterface public interface OptionalIntPredicate {
	/**
	 * Variation of {@link IntPredicate#test(int)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalIntPredicate} is obtained from {@link ExceptionHandler#fromIntPredicate(IntPredicate)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code IntPredicate} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code IntPredicate}.
	 * 
	 * @param value
	 *            see {@link IntPredicate#test(int)}
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link IntPredicate#test(int)},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntPredicate(IntPredicate)
	 * @see IntPredicate#test(int)
	 */
	OptionalBoolean test(int value);
	/**
	 * Convert this {@code OptionalIntPredicate} to plain {@code IntPredicate} using default value.
	 * The returned {@code IntPredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code IntPredicate} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default IntPredicate orElse(boolean result) {
		return new DefaultIntPredicate(this, result);
	}
	/**
	 * Convert this {@code OptionalIntPredicate} to plain {@code IntPredicate} using fallback {@code BooleanSupplier}.
	 * The returned {@code IntPredicate} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code IntPredicate} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default IntPredicate orElseGet(BooleanSupplier source) {
		return new FallbackIntPredicate(this, source);
	}
}
