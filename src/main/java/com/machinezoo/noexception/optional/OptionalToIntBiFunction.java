// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToIntBiFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalToIntBiFunction} is typically obtained from {@link ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)},
 * in which case its return value is empty when the underlying {@link ToIntBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToIntBiFunction}
 * @param <U>
 *            see {@link ToIntBiFunction}
 * @see ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)
 * @see ToIntBiFunction
 */
@FunctionalInterface
public interface OptionalToIntBiFunction<T, U> extends BiFunction<T, U, OptionalInt> {
	/**
	 * Variation of {@link ToIntBiFunction#applyAsInt(Object, Object)} that returns {@link OptionalInt}.
	 * If this {@code OptionalToIntBiFunction} is obtained from {@link ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)},
	 * the {@link OptionalInt} will be empty only if the underlying {@link ToIntBiFunction} throws.
	 * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link ToIntBiFunction}.
	 * 
	 * @param t
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
	 * @param u
	 *            see {@link ToIntBiFunction#applyAsInt(Object, Object)}
 * @return {@link OptionalInt} typically wrapping return value of {@link ToIntBiFunction#applyAsInt(Object, Object)},
 *         or an empty {@link OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromToIntBiFunction(ToIntBiFunction)
 * @see ToIntBiFunction#applyAsInt(Object, Object)
 */
	@Override
	OptionalInt apply(T t, U u);
	/**
	 * Converts this {@code OptionalToIntBiFunction} to plain {@link ToIntBiFunction} using default value.
	 * The returned {@link ToIntBiFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or return {@code result} if the {@link OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalInt}
	 * @return plain {@link ToIntBiFunction} that either unwraps {@link OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default ToIntBiFunction<T, U> orElse(int result) {
		return new DefaultToIntBiFunction<T, U>(this, result);
	}
	/**
	 * Converts this {@code OptionalToIntBiFunction} to plain {@link ToIntBiFunction} using fallback {@link IntSupplier}.
	 * The returned {@link ToIntBiFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
	 * @return plain {@link ToIntBiFunction} that either unwraps {@link OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default ToIntBiFunction<T, U> orElseGet(IntSupplier source) {
		return new FallbackToIntBiFunction<T, U>(this, source);
	}
}
