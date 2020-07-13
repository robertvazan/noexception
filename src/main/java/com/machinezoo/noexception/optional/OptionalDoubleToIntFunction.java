// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalDoubleToIntFunction} is typically obtained from {@link ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)},
 * in which case its return value is empty when the underlying {@code DoubleToIntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)
 * @see DoubleToIntFunction
 */
@FunctionalInterface
public interface OptionalDoubleToIntFunction extends DoubleFunction<OptionalInt> {
	/**
	 * Variation of {@link DoubleToIntFunction#applyAsInt(double)} that returns {@link OptionalInt}.
	 * If this {@code OptionalDoubleToIntFunction} is obtained from {@link ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code DoubleToIntFunction} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code DoubleToIntFunction}.
	 * 
	 * @param value
	 *            see {@link DoubleToIntFunction#applyAsInt(double)}
 * @return {@code OptionalInt} typically wrapping return value of {@link DoubleToIntFunction#applyAsInt(double)},
 *         or an empty {@code OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)
 * @see DoubleToIntFunction#applyAsInt(double)
 */
	@Override
	OptionalInt apply(double value);
	/**
	 * Converts this {@code OptionalDoubleToIntFunction} to plain {@code DoubleToIntFunction} using default value.
	 * The returned {@code DoubleToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code DoubleToIntFunction} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default DoubleToIntFunction orElse(int result) {
		return new DefaultDoubleToIntFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleToIntFunction} to plain {@code DoubleToIntFunction} using fallback {@code IntSupplier}.
	 * The returned {@code DoubleToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code DoubleToIntFunction} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default DoubleToIntFunction orElseGet(IntSupplier source) {
		return new FallbackDoubleToIntFunction(this, source);
	}
}
