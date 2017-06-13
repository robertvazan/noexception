// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalDoubleFunction} is typically obtained from {@link ExceptionHandler#fromDoubleFunction(DoubleFunction)},
 * in which case its return value is empty when the underlying {@code DoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link DoubleFunction}
 * @see ExceptionHandler#fromDoubleFunction(DoubleFunction)
 * @see DoubleFunction
 */
@FunctionalInterface public interface OptionalDoubleFunction<R> extends DoubleFunction<Optional<R>> {
	/**
	 * Variation of {@link DoubleFunction#apply(double)} that returns {@link Optional}.
	 * If this {@code OptionalDoubleFunction} is obtained from {@link ExceptionHandler#fromDoubleFunction(DoubleFunction)},
	 * the {@code Optional} will be empty only if the underlying {@code DoubleFunction} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code DoubleFunction} (possibly {@code null}).
	 * 
	 * @param value
	 *            see {@link DoubleFunction#apply(double)}
	 * @return {@code Optional} typically wrapping return value of {@link DoubleFunction#apply(double)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromDoubleFunction(DoubleFunction)
	 * @see DoubleFunction#apply(double)
	 */
	@Override Optional<R> apply(double value);
	/**
	 * Convert this {@code OptionalDoubleFunction} to plain {@code DoubleFunction} using default value.
	 * The returned {@code DoubleFunction} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code DoubleFunction} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default DoubleFunction<R> orElse(R result) {
		return new DefaultDoubleFunction<R>(this, result);
	}
	/**
	 * Convert this {@code OptionalDoubleFunction} to plain {@code DoubleFunction} using fallback {@code Supplier}.
	 * The returned {@code DoubleFunction} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code DoubleFunction} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default DoubleFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackDoubleFunction<R>(this, source);
	}
}
