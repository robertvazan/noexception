// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntSupplier} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalIntSupplier} is typically obtained from {@link ExceptionHandler#fromIntSupplier(IntSupplier)},
 * in which case its return value is empty when the underlying {@link IntSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntSupplier(IntSupplier)
 * @see IntSupplier
 */
@FunctionalInterface
public interface OptionalIntSupplier extends Supplier<OptionalInt> {
	/**
	 * Variation of {@link IntSupplier#getAsInt()} that returns {@link OptionalInt}.
	 * If this {@code OptionalIntSupplier} is obtained from {@link ExceptionHandler#fromIntSupplier(IntSupplier)},
	 * the {@link OptionalInt} will be empty only if the underlying {@link IntSupplier} throws.
	 * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link IntSupplier}.
	 * 
 * @return {@link OptionalInt} typically wrapping return value of {@link IntSupplier#getAsInt()},
 *         or an empty {@link OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromIntSupplier(IntSupplier)
 * @see IntSupplier#getAsInt()
 */
	@Override
	OptionalInt get();
	/**
	 * Converts this {@code OptionalIntSupplier} to plain {@link IntSupplier} using default value.
	 * The returned {@link IntSupplier} will unwrap present value from the {@link OptionalInt} if possible,
	 * or return {@code result} if the {@link OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalInt}
	 * @return plain {@link IntSupplier} that either unwraps {@link OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default IntSupplier orElse(int result) {
		return new DefaultIntSupplier(this, result);
	}
	/**
	 * Converts this {@code OptionalIntSupplier} to plain {@link IntSupplier} using fallback {@link IntSupplier}.
	 * The returned {@link IntSupplier} will unwrap present value from the {@link OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
	 * @return plain {@link IntSupplier} that either unwraps {@link OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default IntSupplier orElseGet(IntSupplier source) {
		return new FallbackIntSupplier(this, source);
	}
}
