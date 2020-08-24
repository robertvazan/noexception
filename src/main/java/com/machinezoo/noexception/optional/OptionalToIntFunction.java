// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalToIntFunction} is typically obtained from {@link ExceptionHandler#fromToIntFunction(ToIntFunction)},
 * in which case its return value is empty when the underlying {@link ToIntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntFunction}
 * @see ExceptionHandler#fromToIntFunction(ToIntFunction)
 * @see ToIntFunction
 */
@FunctionalInterface
public interface OptionalToIntFunction<T> extends Function<T, OptionalInt> {
	/**
	 * Variation of {@link ToIntFunction#applyAsInt(Object)} that returns {@link OptionalInt}.
	 * If this {@code OptionalToIntFunction} is obtained from {@link ExceptionHandler#fromToIntFunction(ToIntFunction)},
	 * the {@link OptionalInt} will be empty only if the underlying {@link ToIntFunction} throws.
	 * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link ToIntFunction}.
	 * 
	 * @param value
	 *            see {@link ToIntFunction#applyAsInt(Object)}
 * @return {@link OptionalInt} typically wrapping return value of {@link ToIntFunction#applyAsInt(Object)},
 *         or an empty {@link OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromToIntFunction(ToIntFunction)
 * @see ToIntFunction#applyAsInt(Object)
 */
	@Override
	OptionalInt apply(T value);
	/**
	 * Converts this {@code OptionalToIntFunction} to plain {@link ToIntFunction} using default value.
	 * The returned {@link ToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or return {@code result} if the {@link OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalInt}
	 * @return plain {@link ToIntFunction} that either unwraps {@link OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default ToIntFunction<T> orElse(int result) {
		return new DefaultToIntFunction<T>(this, result);
	}
	/**
	 * Converts this {@code OptionalToIntFunction} to plain {@link ToIntFunction} using fallback {@link IntSupplier}.
	 * The returned {@link ToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
	 * @return plain {@link ToIntFunction} that either unwraps {@link OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default ToIntFunction<T> orElseGet(IntSupplier source) {
		return new FallbackToIntFunction<T>(this, source);
	}
}
