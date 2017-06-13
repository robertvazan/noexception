// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntBinaryOperator} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalIntBinaryOperator} is typically obtained from {@link ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)},
 * in which case its return value is empty when the underlying {@code IntBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)
 * @see IntBinaryOperator
 */
@FunctionalInterface public interface OptionalIntBinaryOperator {
	/**
	 * Variation of {@link IntBinaryOperator#applyAsInt(int, int)} that returns {@link OptionalInt}.
	 * If this {@code OptionalIntBinaryOperator} is obtained from {@link ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code IntBinaryOperator} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code IntBinaryOperator}.
	 * 
	 * @param left
	 *            see {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @param right
	 *            see {@link IntBinaryOperator#applyAsInt(int, int)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link IntBinaryOperator#applyAsInt(int, int)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)
	 * @see IntBinaryOperator#applyAsInt(int, int)
	 */
	OptionalInt apply(int left, int right);
	/**
	 * Convert this {@code OptionalIntBinaryOperator} to plain {@code IntBinaryOperator} using default value.
	 * The returned {@code IntBinaryOperator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code IntBinaryOperator} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default IntBinaryOperator orElse(int result) {
		return new DefaultIntBinaryOperator(this, result);
	}
	/**
	 * Convert this {@code OptionalIntBinaryOperator} to plain {@code IntBinaryOperator} using fallback {@code IntSupplier}.
	 * The returned {@code IntBinaryOperator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code IntBinaryOperator} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default IntBinaryOperator orElseGet(IntSupplier source) {
		return new FallbackIntBinaryOperator(this, source);
	}
}
