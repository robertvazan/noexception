// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongUnaryOperator} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalLongUnaryOperator} is typically obtained from {@link ExceptionHandler#fromLongUnaryOperator(LongUnaryOperator)},
 * in which case its return value is empty when the underlying {@link LongUnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongUnaryOperator(LongUnaryOperator)
 * @see LongUnaryOperator
 */
@FunctionalInterface
public interface OptionalLongUnaryOperator extends LongFunction<OptionalLong> {
	/**
	 * Variation of {@link LongUnaryOperator#applyAsLong(long)} that returns {@link OptionalLong}.
	 * If this {@code OptionalLongUnaryOperator} is obtained from {@link ExceptionHandler#fromLongUnaryOperator(LongUnaryOperator)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link LongUnaryOperator} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link LongUnaryOperator}.
	 * 
	 * @param operand
	 *            see {@link LongUnaryOperator#applyAsLong(long)}
	 * @return {@link OptionalLong} typically wrapping return value of {@link LongUnaryOperator#applyAsLong(long)},
	 *         or an empty {@link OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongUnaryOperator(LongUnaryOperator)
	 * @see LongUnaryOperator#applyAsLong(long)
	 */
	@Override
	OptionalLong apply(long operand);
	/**
	 * Converts this {@code OptionalLongUnaryOperator} to plain {@link LongUnaryOperator} using default value.
	 * The returned {@link LongUnaryOperator} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link LongUnaryOperator} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default LongUnaryOperator orElse(long result) {
		return new DefaultLongUnaryOperator(this, result);
	}
	/**
	 * Converts this {@code OptionalLongUnaryOperator} to plain {@link LongUnaryOperator} using fallback {@link LongSupplier}.
	 * The returned {@link LongUnaryOperator} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link LongUnaryOperator} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default LongUnaryOperator orElseGet(LongSupplier source) {
		return new FallbackLongUnaryOperator(this, source);
	}
}
