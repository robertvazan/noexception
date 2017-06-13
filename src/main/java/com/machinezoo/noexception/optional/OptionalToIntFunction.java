// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalToIntFunction} is typically obtained from {@link ExceptionHandler#fromToIntFunction(ToIntFunction)},
 * in which case its return value is empty when the underlying {@code ToIntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntFunction}
 * @see ExceptionHandler#fromToIntFunction(ToIntFunction)
 * @see ToIntFunction
 */
@FunctionalInterface public interface OptionalToIntFunction<T> extends Function<T, OptionalInt> {
	/**
	 * Variation of {@link ToIntFunction#applyAsInt(Object)} that returns {@link OptionalInt}.
	 * If this {@code OptionalToIntFunction} is obtained from {@link ExceptionHandler#fromToIntFunction(ToIntFunction)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code ToIntFunction} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code ToIntFunction}.
	 * 
	 * @param value
	 *            see {@link ToIntFunction#applyAsInt(Object)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link ToIntFunction#applyAsInt(Object)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromToIntFunction(ToIntFunction)
	 * @see ToIntFunction#applyAsInt(Object)
	 */
	@Override OptionalInt apply(T value);
	/**
	 * Convert this {@code OptionalToIntFunction} to plain {@code ToIntFunction} using default value.
	 * The returned {@code ToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code ToIntFunction} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default ToIntFunction<T> orElse(int result) {
		return new DefaultToIntFunction<T>(this, result);
	}
	/**
	 * Convert this {@code OptionalToIntFunction} to plain {@code ToIntFunction} using fallback {@code IntSupplier}.
	 * The returned {@code ToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code ToIntFunction} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default ToIntFunction<T> orElseGet(IntSupplier source) {
		return new FallbackToIntFunction<T>(this, source);
	}
}
