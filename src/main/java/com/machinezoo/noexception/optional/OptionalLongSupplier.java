// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongSupplier} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalLongSupplier} is typically obtained from {@link ExceptionHandler#fromLongSupplier(LongSupplier)},
 * in which case its return value is empty when the underlying {@code LongSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongSupplier(LongSupplier)
 * @see LongSupplier
 */
@FunctionalInterface public interface OptionalLongSupplier extends Supplier<OptionalLong> {
	/**
	 * Variation of {@link LongSupplier#getAsLong()} that returns {@link OptionalLong}.
	 * If this {@code OptionalLongSupplier} is obtained from {@link ExceptionHandler#fromLongSupplier(LongSupplier)},
	 * the {@code OptionalLong} will be empty only if the underlying {@code LongSupplier} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code LongSupplier}.
	 * 
	 * @return {@code OptionalLong} typically wrapping return value of {@link LongSupplier#getAsLong()},
	 *         or an empty {@code OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongSupplier(LongSupplier)
	 * @see LongSupplier#getAsLong()
	 */
	@Override OptionalLong get();
	/**
	 * Convert this {@code OptionalLongSupplier} to plain {@code LongSupplier} using default value.
	 * The returned {@code LongSupplier} will unwrap present value from the {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code LongSupplier} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default LongSupplier orElse(long result) {
		return new DefaultLongSupplier(this, result);
	}
	/**
	 * Convert this {@code OptionalLongSupplier} to plain {@code LongSupplier} using fallback {@code LongSupplier}.
	 * The returned {@code LongSupplier} will unwrap present value from the {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code LongSupplier} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default LongSupplier orElseGet(LongSupplier source) {
		return new FallbackLongSupplier(this, source);
	}
}
