// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BooleanSupplier} that returns {@link OptionalBoolean} instead of the raw value.
 * {@code OptionalBooleanSupplier} is typically obtained from {@link ExceptionHandler#fromBooleanSupplier(BooleanSupplier)},
 * in which case its return value is empty when the underlying {@link BooleanSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromBooleanSupplier(BooleanSupplier)
 * @see BooleanSupplier
 */
@FunctionalInterface
public interface OptionalBooleanSupplier extends Supplier<OptionalBoolean> {
	/**
	 * Variation of {@link BooleanSupplier#getAsBoolean()} that returns {@link OptionalBoolean}.
	 * If this {@code OptionalBooleanSupplier} is obtained from {@link ExceptionHandler#fromBooleanSupplier(BooleanSupplier)},
	 * the {@link OptionalBoolean} will be empty only if the underlying {@link BooleanSupplier} throws.
	 * Otherwise the returned {@link OptionalBoolean} just wraps the return value of underlying {@link BooleanSupplier}.
	 * 
 * @return {@link OptionalBoolean} typically wrapping return value of {@link BooleanSupplier#getAsBoolean()},
 *         or an empty {@link OptionalBoolean} (typically signifying an exception)
 * @see ExceptionHandler#fromBooleanSupplier(BooleanSupplier)
 * @see BooleanSupplier#getAsBoolean()
 */
	@Override
	OptionalBoolean get();
	/**
	 * Converts this {@code OptionalBooleanSupplier} to plain {@link BooleanSupplier} using default value.
	 * The returned {@link BooleanSupplier} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or return {@code result} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalBoolean}
	 * @return plain {@link BooleanSupplier} that either unwraps {@link OptionalBoolean} or returns default value
	 * @see #orElseGet(BooleanSupplier)
	 * @see OptionalBoolean#orElse(boolean)
	 */
	default BooleanSupplier orElse(boolean result) {
		return new DefaultBooleanSupplier(this, result);
	}
	/**
	 * Converts this {@code OptionalBooleanSupplier} to plain {@link BooleanSupplier} using fallback {@link BooleanSupplier}.
	 * The returned {@link BooleanSupplier} will unwrap present value from the {@link OptionalBoolean} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalBoolean} is empty.
	 * 
	 * @param source
	 *            {@link BooleanSupplier} to query for fallback value when {@link OptionalBoolean} is empty
	 * @return plain {@link BooleanSupplier} that either unwraps {@link OptionalBoolean} or falls back to {@code source}
	 * @see #orElse(boolean)
	 * @see OptionalBoolean#orElseGet(BooleanSupplier)
	 */
	default BooleanSupplier orElseGet(BooleanSupplier source) {
		return new FallbackBooleanSupplier(this, source);
	}
}
