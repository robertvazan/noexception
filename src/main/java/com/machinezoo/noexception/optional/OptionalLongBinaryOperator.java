// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongBinaryOperator} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalLongBinaryOperator} is typically obtained from {@link ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)},
 * in which case its return value is empty when the underlying {@link LongBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)
 * @see LongBinaryOperator
 */
@FunctionalInterface
public interface OptionalLongBinaryOperator {
	/**
	 * Variation of {@link LongBinaryOperator#applyAsLong(long, long)} that returns {@link OptionalLong}.
	 * If this {@code OptionalLongBinaryOperator} is obtained from {@link ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link LongBinaryOperator} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link LongBinaryOperator}.
	 * 
	 * @param left
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
	 * @param right
	 *            see {@link LongBinaryOperator#applyAsLong(long, long)}
 * @return {@link OptionalLong} typically wrapping return value of {@link LongBinaryOperator#applyAsLong(long, long)},
 *         or an empty {@link OptionalLong} (typically signifying an exception)
 * @see ExceptionHandler#fromLongBinaryOperator(LongBinaryOperator)
 * @see LongBinaryOperator#applyAsLong(long, long)
 */
	OptionalLong apply(long left, long right);
	/**
	 * Converts this {@code OptionalLongBinaryOperator} to plain {@link LongBinaryOperator} using default value.
	 * The returned {@link LongBinaryOperator} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link LongBinaryOperator} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default LongBinaryOperator orElse(long result) {
		return new DefaultLongBinaryOperator(this, result);
	}
	/**
	 * Converts this {@code OptionalLongBinaryOperator} to plain {@link LongBinaryOperator} using fallback {@link LongSupplier}.
	 * The returned {@link LongBinaryOperator} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link LongBinaryOperator} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default LongBinaryOperator orElseGet(LongSupplier source) {
		return new FallbackLongBinaryOperator(this, source);
	}
}
