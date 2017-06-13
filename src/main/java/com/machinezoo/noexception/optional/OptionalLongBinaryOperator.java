// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongBinaryOperator} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalLongBinaryOperator} is typically obtained from {@link ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)},
 * in which case its return value is empty when the underlying {@code LongBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)
 * @see LongBinaryOperator
 */
@FunctionalInterface public interface OptionalLongBinaryOperator {
	/**
	 * Variation of {@link LongBinaryOperator#applyAsLong(long, long)} that returns {@link OptionalLong}.
	 * If this {@code OptionalLongBinaryOperator} is obtained from {@link ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)},
	 * the {@code OptionalLong} will be empty only if the underlying {@code LongBinaryOperator} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code LongBinaryOperator}.
	 * 
	 * @param left
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @param right
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @return {@code OptionalLong} typically wrapping return value of {@link LongBinaryOperator#applyAsLong(long, long)},
	 *         or an empty {@code OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)
	 * @see LongBinaryOperator#applyAsLong(long, long)
	 */
	OptionalLong apply(long left, long right);
	/**
	 * Convert this {@code OptionalLongBinaryOperator} to plain {@code LongBinaryOperator} using default value.
	 * The returned {@code LongBinaryOperator} will unwrap present value from the {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code LongBinaryOperator} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default LongBinaryOperator orElse(long result) {
		return new DefaultLongBinaryOperator(this, result);
	}
	/**
	 * Convert this {@code OptionalLongBinaryOperator} to plain {@code LongBinaryOperator} using fallback {@code LongSupplier}.
	 * The returned {@code LongBinaryOperator} will unwrap present value from the {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code LongBinaryOperator} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default LongBinaryOperator orElseGet(LongSupplier source) {
		return new FallbackLongBinaryOperator(this, source);
	}
}
