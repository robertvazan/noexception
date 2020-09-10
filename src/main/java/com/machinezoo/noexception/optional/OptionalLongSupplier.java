// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongSupplier} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalLongSupplier} is typically obtained from {@link ExceptionHandler#fromLongSupplier(LongSupplier)},
 * in which case its return value is empty when the underlying {@link LongSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongSupplier(LongSupplier)
 * @see LongSupplier
 */
@FunctionalInterface
public interface OptionalLongSupplier extends Supplier<OptionalLong> {
	/**
	 * Variation of {@link LongSupplier#getAsLong()} that returns {@link OptionalLong}.
	 * If this {@code OptionalLongSupplier} is obtained from {@link ExceptionHandler#fromLongSupplier(LongSupplier)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link LongSupplier} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link LongSupplier}.
	 * 
	 * @return {@link OptionalLong} typically wrapping return value of {@link LongSupplier#getAsLong()},
	 *         or an empty {@link OptionalLong} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongSupplier(LongSupplier)
	 * @see LongSupplier#getAsLong()
	 */
	@Override
	OptionalLong get();
	/**
	 * Converts this {@code OptionalLongSupplier} to plain {@link LongSupplier} using default value.
	 * The returned {@link LongSupplier} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link LongSupplier} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default LongSupplier orElse(long result) {
		return new DefaultLongSupplier(this, result);
	}
	/**
	 * Converts this {@code OptionalLongSupplier} to plain {@link LongSupplier} using fallback {@link LongSupplier}.
	 * The returned {@link LongSupplier} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link LongSupplier} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default LongSupplier orElseGet(LongSupplier source) {
		return new FallbackLongSupplier(this, source);
	}
}
