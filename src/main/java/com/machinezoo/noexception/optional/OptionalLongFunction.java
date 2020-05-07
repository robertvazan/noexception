// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalLongFunction} is typically obtained from {@link ExceptionHandler#fromLongFunction(LongFunction)},
 * in which case its return value is empty when the underlying {@code LongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link LongFunction}
 * @see ExceptionHandler#fromLongFunction(LongFunction)
 * @see LongFunction
 */
@FunctionalInterface public interface OptionalLongFunction<R> extends LongFunction<Optional<R>> {
	/**
	 * Variation of {@link LongFunction#apply(long)} that returns {@link Optional}.
	 * If this {@code OptionalLongFunction} is obtained from {@link ExceptionHandler#fromLongFunction(LongFunction)},
	 * the {@code Optional} will be empty only if the underlying {@code LongFunction} throws.
	 * Otherwise the returned {@code Optional} just wraps the return value of underlying {@code LongFunction} (possibly {@code null}).
	 * 
	 * @param value
	 *            see {@link LongFunction#apply(long)}
	 * @return {@code Optional} typically wrapping return value of {@link LongFunction#apply(long)},
	 *         or an empty {@code Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongFunction(LongFunction)
	 * @see LongFunction#apply(long)
	 */
	@Override Optional<R> apply(long value);
	/**
	 * Converts this {@code OptionalLongFunction} to plain {@code LongFunction} using default value.
	 * The returned {@code LongFunction} will unwrap present value from the {@code Optional} if possible,
	 * or return {@code result} if the {@code Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code Optional}
	 * @return plain {@code LongFunction} that either unwraps {@code Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default LongFunction<R> orElse(R result) {
		return new DefaultLongFunction<R>(this, result);
	}
	/**
	 * Converts this {@code OptionalLongFunction} to plain {@code LongFunction} using fallback {@code Supplier}.
	 * The returned {@code LongFunction} will unwrap present value from the {@code Optional} if possible,
	 * or fall back to calling {@code source} if the {@code Optional} is empty.
	 * 
	 * @param source
	 *            {@code Supplier} to query for fallback value when {@code Optional} is empty
	 * @return plain {@code LongFunction} that either unwraps {@code Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default LongFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackLongFunction<R>(this, source);
	}
}
