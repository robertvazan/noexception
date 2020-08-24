// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleSupplier} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalDoubleSupplier} is typically obtained from {@link ExceptionHandler#fromDoubleSupplier(DoubleSupplier)},
 * in which case its return value is empty when the underlying {@link DoubleSupplier} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleSupplier(DoubleSupplier)
 * @see DoubleSupplier
 */
@FunctionalInterface
public interface OptionalDoubleSupplier extends Supplier<OptionalDouble> {
	/**
	 * Variation of {@link DoubleSupplier#getAsDouble()} that returns {@link OptionalDouble}.
	 * If this {@code OptionalDoubleSupplier} is obtained from {@link ExceptionHandler#fromDoubleSupplier(DoubleSupplier)},
	 * the {@link OptionalDouble} will be empty only if the underlying {@link DoubleSupplier} throws.
	 * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link DoubleSupplier}.
	 * 
 * @return {@link OptionalDouble} typically wrapping return value of {@link DoubleSupplier#getAsDouble()},
 *         or an empty {@link OptionalDouble} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleSupplier(DoubleSupplier)
 * @see DoubleSupplier#getAsDouble()
 */
	@Override
	OptionalDouble get();
	/**
	 * Converts this {@code OptionalDoubleSupplier} to plain {@link DoubleSupplier} using default value.
	 * The returned {@link DoubleSupplier} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or return {@code result} if the {@link OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalDouble}
	 * @return plain {@link DoubleSupplier} that either unwraps {@link OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default DoubleSupplier orElse(double result) {
		return new DefaultDoubleSupplier(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleSupplier} to plain {@link DoubleSupplier} using fallback {@link DoubleSupplier}.
	 * The returned {@link DoubleSupplier} will unwrap present value from the {@link OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
	 * @return plain {@link DoubleSupplier} that either unwraps {@link OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default DoubleSupplier orElseGet(DoubleSupplier source) {
		return new FallbackDoubleSupplier(this, source);
	}
}
