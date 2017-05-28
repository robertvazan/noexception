// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BooleanSupplier} that returns {@code OptionalBoolean} instead of the raw value.
 * {@code OptionalBooleanSupplier} is typically obtained from {@link ExceptionHandler#fromBooleanSupplier(BooleanSupplier)},
 * in which case its return value is empty when the underlying {@code BooleanSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see ExceptionHandler#fromBooleanSupplier(BooleanSupplier)
 * @see BooleanSupplier
 */
@FunctionalInterface public interface OptionalBooleanSupplier extends Supplier<OptionalBoolean> {
	/**
	 * Variation of {@link BooleanSupplier#getAsBoolean()} that returns {@code OptionalBoolean}.
	 * If this {@code OptionalBooleanSupplier} is obtained from {@link ExceptionHandler#fromBooleanSupplier(BooleanSupplier)},
	 * the {@code OptionalBoolean} will be empty only if the underlying {@code BooleanSupplier} throws.
	 * Otherwise the returned {@code OptionalBoolean} just wraps the return value of underlying {@code BooleanSupplier}.
	 * 
	 * @return {@code OptionalBoolean} typically wrapping return value of {@link BooleanSupplier#getAsBoolean()},
	 *         or an empty {@code OptionalBoolean} (typically signifying an exception)
	 * @see ExceptionHandler#fromBooleanSupplier(BooleanSupplier)
	 * @see BooleanSupplier#getAsBoolean()
	 */
	@Override OptionalBoolean get();
	/**
	 * Convert this {@code OptionalBooleanSupplier} to plain {@code BooleanSupplier} using default value.
	 * The returned {@code BooleanSupplier} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or return {@code result} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalBoolean}
	 * @return plain {@code BooleanSupplier} that either unwraps {@code OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default BooleanSupplier orElse(boolean result) {
		return new DefaultBooleanSupplier(this, result);
	}
	/**
	 * Convert this {@code OptionalBooleanSupplier} to plain {@code BooleanSupplier} using fallback {@code BooleanSupplier}.
	 * The returned {@code BooleanSupplier} will unwrap present value from the {@code OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@code BooleanSupplier} to query for fallback value when {@code OptionalBoolean} is empty
	 * @return plain {@code BooleanSupplier} that either unwraps {@code OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default BooleanSupplier orElseGet(BooleanSupplier source) {
		return new FallbackBooleanSupplier(this, source);
	}
}
