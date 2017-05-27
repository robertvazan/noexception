// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link IntSupplier} that returns {@code OptionalInt} instead of the raw value.
 * {@code OptionalIntSupplier} is typically obtained from {@link ExceptionHandler#fromIntSupplier(IntSupplier)},
 * in which case its return value is empty when the underlying {@code IntSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntSupplier(IntSupplier)
 * @see IntSupplier
 */
@FunctionalInterface public interface OptionalIntSupplier extends Supplier<OptionalInt> {
	/**
	 * Variation of {@link IntSupplier#getAsInt()} that returns {@code OptionalInt}.
	 * If this {@code OptionalIntSupplier} is obtained from {@link ExceptionHandler#fromIntSupplier(IntSupplier)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code IntSupplier} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code IntSupplier}.
	 * 
	 * @return {@code OptionalInt} typically wrapping return value of {@link IntSupplier#getAsInt()},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntSupplier(IntSupplier)
	 * @see IntSupplier#getAsInt()
	 */
	@Override OptionalInt get();
	/**
	 * Convert this {@code OptionalIntSupplier} to plain {@code IntSupplier} using default value.
	 * The returned {@code IntSupplier} will unwrap present value from {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code IntSupplier} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default IntSupplier orElse(int result) {
		return new DefaultIntSupplier(this, result);
	}
	/**
	 * Convert this {@code OptionalIntSupplier} to plain {@code IntSupplier} using fallback {@code IntSupplier}.
	 * The returned {@code IntSupplier} will unwrap present value from {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code IntSupplier} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default IntSupplier orElseGet(IntSupplier source) {
		return new FallbackIntSupplier(this, source);
	}
}
