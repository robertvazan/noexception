// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalLongFunction} is typically obtained from {@link ExceptionHandler#fromLongFunction(LongFunction)},
 * in which case its return value is empty when the underlying {@link LongFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link LongFunction}
 * @see ExceptionHandler#fromLongFunction(LongFunction)
 * @see LongFunction
 */
@FunctionalInterface
public interface OptionalLongFunction<R> extends LongFunction<Optional<R>> {
	/**
	 * Variation of {@link LongFunction#apply(long)} that returns {@link Optional}.
	 * If this {@code OptionalLongFunction} is obtained from {@link ExceptionHandler#fromLongFunction(LongFunction)},
	 * the {@link Optional} will be empty only if the underlying {@link LongFunction} throws.
	 * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link LongFunction} (possibly {@code null}).
	 * 
	 * @param value
	 *            see {@link LongFunction#apply(long)}
	 * @return {@link Optional} typically wrapping return value of {@link LongFunction#apply(long)},
	 *         or an empty {@link Optional} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongFunction(LongFunction)
	 * @see LongFunction#apply(long)
	 */
	@Override
	Optional<R> apply(long value);
	/**
	 * Converts this {@code OptionalLongFunction} to plain {@link LongFunction} using default value.
	 * The returned {@link LongFunction} will unwrap present value from the {@link Optional} if possible,
	 * or return {@code result} if the {@link Optional} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link Optional}
	 * @return plain {@link LongFunction} that either unwraps {@link Optional} or returns default value
	 * @see #orElseGet(Supplier)
	 * @see Optional#orElse(Object)
	 */
	default LongFunction<R> orElse(R result) {
		return new DefaultLongFunction<R>(this, result);
	}
	/**
	 * Converts this {@code OptionalLongFunction} to plain {@link LongFunction} using fallback {@link Supplier}.
	 * The returned {@link LongFunction} will unwrap present value from the {@link Optional} if possible,
	 * or fall back to calling {@code source} if the {@link Optional} is empty.
	 * 
	 * @param source
	 *            {@link Supplier} to query for fallback value when {@link Optional} is empty
	 * @return plain {@link LongFunction} that either unwraps {@link Optional} or falls back to {@code source}
	 * @see #orElse(Object)
	 * @see Optional#orElseGet(Supplier)
	 */
	default LongFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackLongFunction<R>(this, source);
	}
}
