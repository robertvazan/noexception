// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleToLongFunction} that returns {@link OptionalLong} instead of the raw value.
 * {@code OptionalDoubleToLongFunction} is typically obtained from {@link ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)},
 * in which case its return value is empty when the underlying {@code DoubleToLongFunction} throws an exception.
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
	 * the {@code OptionalLong} will be empty only if the underlying {@code DoubleToLongFunction} throws.
	 * Otherwise the returned {@code OptionalLong} just wraps the return value of underlying {@code DoubleToLongFunction}.
	 * 
	 * @param value
	 *            see {@link DoubleToLongFunction#applyAsLong(double)}
 * @return {@code OptionalLong} typically wrapping return value of {@link DoubleToLongFunction#applyAsLong(double)},
 *         or an empty {@code OptionalLong} (typically signifying an exception)
 * @see ExceptionHandler#fromDoubleToLongFunction(DoubleToLongFunction)
 * @see DoubleToLongFunction#applyAsLong(double)
 */
	@Override
	OptionalLong apply(double value);
	/**
	 * Converts this {@code OptionalDoubleToLongFunction} to plain {@code DoubleToLongFunction} using default value.
	 * The returned {@code DoubleToLongFunction} will unwrap present value from the {@code OptionalLong} if possible,
	 * or return {@code result} if the {@code OptionalLong} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalLong}
	 * @return plain {@code DoubleToLongFunction} that either unwraps {@code OptionalLong} or returns default value
	 * @see #orElseGet(LongSupplier)
	 * @see OptionalLong#orElse(long)
	 */
	default DoubleToLongFunction orElse(long result) {
		return new DefaultDoubleToLongFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalDoubleToLongFunction} to plain {@code DoubleToLongFunction} using fallback {@code LongSupplier}.
	 * The returned {@code DoubleToLongFunction} will unwrap present value from the {@code OptionalLong} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalLong} is empty.
	 * 
	 * @param source
	 *            {@code LongSupplier} to query for fallback value when {@code OptionalLong} is empty
	 * @return plain {@code DoubleToLongFunction} that either unwraps {@code OptionalLong} or falls back to {@code source}
	 * @see #orElse(long)
	 * @see OptionalLong#orElseGet(LongSupplier)
	 */
	default DoubleToLongFunction orElseGet(LongSupplier source) {
		return new FallbackDoubleToLongFunction(this, source);
	}
}
