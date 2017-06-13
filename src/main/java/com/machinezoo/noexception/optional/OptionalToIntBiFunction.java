// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntBiFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalToIntBiFunction} is typically obtained from {@link ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)},
 * in which case its return value is empty when the underlying {@code ToIntBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntBiFunction}
 * @param <U>
 *            see {@link ToIntBiFunction}
 * @see ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)
 * @see ToIntBiFunction
 */
@FunctionalInterface public interface OptionalToIntBiFunction<T, U> extends BiFunction<T, U, OptionalInt> {
	/**
	 * Variation of {@link ToIntBiFunction#applyAsInt(Object, Object)} that returns {@link OptionalInt}.
	 * If this {@code OptionalToIntBiFunction} is obtained from {@link ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code ToIntBiFunction} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code ToIntBiFunction}.
	 * 
	 * @param t
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @param u
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link ToIntBiFunction#applyAsInt(Object, Object)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)
	 * @see ToIntBiFunction#applyAsInt(Object, Object)
	 */
	@Override OptionalInt apply(T t, U u);
	/**
	 * Convert this {@code OptionalToIntBiFunction} to plain {@code ToIntBiFunction} using default value.
	 * The returned {@code ToIntBiFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code ToIntBiFunction} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default ToIntBiFunction<T, U> orElse(int result) {
		return new DefaultToIntBiFunction<T, U>(this, result);
	}
	/**
	 * Convert this {@code OptionalToIntBiFunction} to plain {@code ToIntBiFunction} using fallback {@code IntSupplier}.
	 * The returned {@code ToIntBiFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code ToIntBiFunction} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default ToIntBiFunction<T, U> orElseGet(IntSupplier source) {
		return new FallbackToIntBiFunction<T, U>(this, source);
	}
}
