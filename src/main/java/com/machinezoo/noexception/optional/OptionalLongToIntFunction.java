// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalLongToIntFunction} is typically obtained from {@link ExceptionHandler#fromLongToIntFunction(LongToIntFunction)},
 * in which case its return value is empty when the underlying {@code LongToIntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongToIntFunction(LongToIntFunction)
 * @see LongToIntFunction
 */
@FunctionalInterface
public interface OptionalLongToIntFunction extends LongFunction<OptionalInt> {
	/**
	 * Variation of {@link LongToIntFunction#applyAsInt(long)} that returns {@link OptionalInt}.
	 * If this {@code OptionalLongToIntFunction} is obtained from {@link ExceptionHandler#fromLongToIntFunction(LongToIntFunction)},
	 * the {@code OptionalInt} will be empty only if the underlying {@code LongToIntFunction} throws.
	 * Otherwise the returned {@code OptionalInt} just wraps the return value of underlying {@code LongToIntFunction}.
	 * 
	 * @param value
	 *            see {@link LongToIntFunction#applyAsInt(long)}
	 * @return {@code OptionalInt} typically wrapping return value of {@link LongToIntFunction#applyAsInt(long)},
	 *         or an empty {@code OptionalInt} (typically signifying an exception)
	 * @see ExceptionHandler#fromLongToIntFunction(LongToIntFunction)
	 * @see LongToIntFunction#applyAsInt(long)
	 */
	@Override
	OptionalInt apply(long value);
	/**
	 * Converts this {@code OptionalLongToIntFunction} to plain {@code LongToIntFunction} using default value.
	 * The returned {@code LongToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or return {@code result} if the {@code OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalInt}
	 * @return plain {@code LongToIntFunction} that either unwraps {@code OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default LongToIntFunction orElse(int result) {
		return new DefaultLongToIntFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalLongToIntFunction} to plain {@code LongToIntFunction} using fallback {@code IntSupplier}.
	 * The returned {@code LongToIntFunction} will unwrap present value from the {@code OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@code IntSupplier} to query for fallback value when {@code OptionalInt} is empty
	 * @return plain {@code LongToIntFunction} that either unwraps {@code OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default LongToIntFunction orElseGet(IntSupplier source) {
		return new FallbackLongToIntFunction(this, source);
	}
}
