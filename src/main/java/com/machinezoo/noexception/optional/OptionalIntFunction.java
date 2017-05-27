// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntFunction} that returns {@code Optional} instead of the raw value.
 * {@code OptionalIntFunction} is typically obtained from {@link ExceptionHandler#fromIntFunction(IntFunction)},
 * in which case its return value is empty when the underlying {@code IntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <R>
 *            see {@link IntFunction}
 * @see ExceptionHandler#fromIntFunction(IntFunction)
 * @see IntFunction
 */
@FunctionalInterface public interface OptionalIntFunction<R> extends IntFunction<Optional<R>> {
	/**
	 * Variation of {@link IntFunction#apply(int)} that returns {@code Optional}.
	 * If this {@code OptionalIntFunction} is obtained from {@link ExceptionHandler#fromIntFunction(IntFunction)},
	 * the {@code Optional} will be empty only if the underlying {@code IntFunction} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code IntFunction} (possibly {@code null}).
	 * 
	 * @param value
	 *            see {@link IntFunction#apply(int)}
	 * @return {@code Optional} typically wrapping return value of {@link IntFunction#apply(int)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromIntFunction(IntFunction)
	 * @see IntFunction#apply(int)
	 */
	@Override Optional<R> apply(int value);
	/**
	 * Convert this {@code OptionalIntFunction} to plain {@code IntFunction} using default value.
	 * The returned {@code IntFunction} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code IntFunction} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default IntFunction<R> orElse(R result) {
		return new DefaultIntFunction<>(this, result);
	}
	/**
	 * Convert this {@code OptionalIntFunction} to plain {@code IntFunction} using fallback {@code Supplier}.
	 * The returned {@code IntFunction} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code IntFunction} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default IntFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackIntFunction<>(this, source);
	}
}
