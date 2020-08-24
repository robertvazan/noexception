// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToLongFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalDoubleToLongFunction} is typically obtained from {@link ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)},
 * in which case its return value is empty when the underlying {@link DoubleToLongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)
 * @see DoubleToLongFunction
 */
@FunctionalInterface
public interface OptionalDoubleToLongFunction extends DoubleFunction<OptionalLong> {
	/**
	 * Variation of {@link DoubleToLongFunction#applyAsLong(double)} that returns {@link OptionalLong}.
	 * If this {@code OptionalDoubleToLongFunction} is obtained from {@link ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)},
	 * the {@link OptionalLong} will be empty only if the underlying {@link DoubleToLongFunction} throws.
	 * Otherwise the returned {@link OptionalLong} just wraps the return value of underlying {@link DoubleToLongFunction}.
	 * 
	 * @param value
	 *            see {@link DoubleToLongFunction#applyAsLong(double)}
 * @return {@link OptionalLong} typically wrapping return value of {@link DoubleToLongFunction#applyAsLong(double)},
 *         or an empty {@link OptionalLong} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)
 * @see DoubleToLongFunction#applyAsLong(double)
 */
	@Override
	OptionalLong apply(double value);
	/**
	 * Converts this {@code OptionalDoubleToLongFunction} to plain {@link DoubleToLongFunction} using default value.
	 * The returned {@link DoubleToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or return {@code result} if the {@link OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalLong}
	 * @return plain {@link DoubleToLongFunction} that either unwraps {@link OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default DoubleToLongFunction orElse(long result) {
		return new DefaultDoubleToLongFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleToLongFunction} to plain {@link DoubleToLongFunction} using fallback {@link LongSupplier}.
	 * The returned {@link DoubleToLongFunction} will unwrap present value from the {@link OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@link LongSupplier} to query for fallback value when {@link OptionalLong} is empty
	 * @return plain {@link DoubleToLongFunction} that either unwraps {@link OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default DoubleToLongFunction orElseGet(LongSupplier source) {
		return new FallbackDoubleToLongFunction(this, source);
	}
}
