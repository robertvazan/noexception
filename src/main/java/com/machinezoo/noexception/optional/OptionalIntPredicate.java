// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntPredicate} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalIntPredicate} is typically obtained from {@link ExceptionHandler#fromIntPredicate(IntPredicate)},
 * in which case its return value is empty when the underlying {@link IntPredicate} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntPredicate(IntPredicate)
 * @see IntPredicate
 */
@FunctionalInterface
public interface OptionalIntPredicate {
	/**
	 * Variation of {@link IntPredicate#test(int)} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalIntPredicate} is obtained from {@link ExceptionHandler#fromIntPredicate(IntPredicate)},
	 * the {@link OptionalBoolean} will be empty only if the underlying {@link IntPredicate} throws.
	 * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link IntPredicate}.
	 * 
	 * @param value
	 *            see {@link IntPredicate#test(int)}
 * @return {@link OptionalBoolean} typically wrapping return value of {@link IntPredicate#test(int)},
 *         or an empty {@link OptionalBoolean} (typically signifying an exception)
 * @see ExceptionHandler#fromIntPredicate(IntPredicate)
 * @see IntPredicate#test(int)
 */
	OptionalBoolean test(int value);
	/**
	 * Converts this {@code OptionalIntPredicate} to plain {@link IntPredicate} using default value.
	 * The returned {@link IntPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or return {@code result} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalBoolean}
	 * @return plain {@link IntPredicate} that either unwraps {@link OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default IntPredicate orElse(boolean result) {
		return new DefaultIntPredicate(this, result);
	}
	/**
	 * Converts this {@code OptionalIntPredicate} to plain {@link IntPredicate} using fallback {@link BooleanSupplier}.
	 * The returned {@link IntPredicate} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
	 * @return plain {@link IntPredicate} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default IntPredicate orElseGet(BooleanSupplier source) {
		return new FallbackIntPredicate(this, source);
	}
}
