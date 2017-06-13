// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleSupplier} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleSupplier} is typically obtained from {@link ExceptionHandler#fromDoubleSupplier(DoubleSupplier)},
 * in which case its return value is empty when the underlying {@code DoubleSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleSupplier(DoubleSupplier)
 * @see DoubleSupplier
 */
@FunctionalInterface public interface OptionalDoubleSupplier extends Supplier<OptionalDouble> {
	/**
	 * Variation of {@link DoubleSupplier#getAsDouble()} that returns {@link OptionalDouble}.
	 * If this {@code OptionalDoubleSupplier} is obtained from {@link ExceptionHandler#fromDoubleSupplier(DoubleSupplier)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code DoubleSupplier} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code DoubleSupplier}.
	 * 
	 * @return {@code OptionalDouble} typically wrapping return value of {@link DoubleSupplier#getAsDouble()},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoubleSupplier(DoubleSupplier)
	 * @see DoubleSupplier#getAsDouble()
	 */
	@Override OptionalDouble get();
	/**
	 * Convert this {@code OptionalDoubleSupplier} to plain {@code DoubleSupplier} using default value.
	 * The returned {@code DoubleSupplier} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code DoubleSupplier} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleSupplier orElse(double result) {
		return new DefaultDoubleSupplier(this, result);
	}
	/**
	 * Convert this {@code OptionalDoubleSupplier} to plain {@code DoubleSupplier} using fallback {@code DoubleSupplier}.
	 * The returned {@code DoubleSupplier} will unwrap present value from the {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code DoubleSupplier} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleSupplier orElseGet(DoubleSupplier source) {
		return new FallbackDoubleSupplier(this, source);
	}
}
