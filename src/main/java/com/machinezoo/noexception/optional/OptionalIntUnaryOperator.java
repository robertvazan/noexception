// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntUnaryOperator} that returns {@code OptionalInt} instead of the raw value.
 * {@code OptionalIntUnaryOperator} is typically obtained from {@link ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)},
 * in which case its return value is empty when the underlying {@code IntUnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)
 * @see IntUnaryOperator
 */
@FunctionalInterface public interface OptionalIntUnaryOperator extends IntFunction<OptionalInt> {
	/**
	 * Variation of {@link IntUnaryOperator#applyAsInt(int)} that returns {@code OptionalInt}.
	 * If this {@code OptionalIntUnaryOperator} is obtained from {@link ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code IntUnaryOperator} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code IntUnaryOperator}.
	 * 
	 * @param operand
	 *            see {@link IntUnaryOperator#applyAsInt(int)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link IntUnaryOperator#applyAsInt(int)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)
	 * @see IntUnaryOperator#applyAsInt(int)
	 */
	@Override OptionalInt apply(int operand);
	/**
	 * Convert this {@code OptionalIntUnaryOperator} to plain {@code IntUnaryOperator} using default value.
	 * The returned {@code IntUnaryOperator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code IntUnaryOperator} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default IntUnaryOperator orElse(int result) {
		return new DefaultIntUnaryOperator(this, result);
	}
	/**
	 * Convert this {@code OptionalIntUnaryOperator} to plain {@code IntUnaryOperator} using fallback {@code IntSupplier}.
	 * The returned {@code IntUnaryOperator} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code IntUnaryOperator} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default IntUnaryOperator orElseGet(IntSupplier source) {
		return new FallbackIntUnaryOperator(this, source);
	}
}
