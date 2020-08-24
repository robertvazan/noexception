// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalDoubleToIntFunction} is typically obtained from {@link ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)},
 * in which case its return value is empty when the underlying {@link DoubleToIntFunction} throws an exception.
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
	 * the {@link OptionalInt} will be empty only if the underlying {@link DoubleToIntFunction} throws.
	 * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link DoubleToIntFunction}.
	 * 
	 * @param value
	 *            see {@link DoubleToIntFunction#applyAsInt(double)}
 * @return {@link OptionalInt} typically wrapping return value of {@link DoubleToIntFunction#applyAsInt(double)},
 *         or an empty {@link OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleToIntFunction(DoubleToIntFunction)
 * @see DoubleToIntFunction#applyAsInt(double)
 */
	@Override
	OptionalInt apply(double value);
	/**
	 * Converts this {@code OptionalDoubleToIntFunction} to plain {@link DoubleToIntFunction} using default value.
	 * The returned {@link DoubleToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or return {@code result} if the {@link OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalInt}
	 * @return plain {@link DoubleToIntFunction} that either unwraps {@link OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default DoubleToIntFunction orElse(int result) {
		return new DefaultDoubleToIntFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleToIntFunction} to plain {@link DoubleToIntFunction} using fallback {@link IntSupplier}.
	 * The returned {@link DoubleToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
	 * @return plain {@link DoubleToIntFunction} that either unwraps {@link OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default DoubleToIntFunction orElseGet(IntSupplier source) {
		return new FallbackDoubleToIntFunction(this, source);
	}
}
