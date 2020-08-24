// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToIntFunction} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalLongToIntFunction} is typically obtained from {@link ExceptionHandler#fromLongToIntFunction(LongToIntFunction)},
 * in which case its return value is empty when the underlying {@link LongToIntFunction} throws an exception.
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
	 * the {@link OptionalInt} will be empty only if the underlying {@link LongToIntFunction} throws.
	 * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link LongToIntFunction}.
	 * 
	 * @param value
	 *            see {@link LongToIntFunction#applyAsInt(long)}
 * @return {@link OptionalInt} typically wrapping return value of {@link LongToIntFunction#applyAsInt(long)},
 *         or an empty {@link OptionalInt} (typically signifying an exception)
 * @see ExceptionHandler#fromLongToIntFunction(LongToIntFunction)
 * @see LongToIntFunction#applyAsInt(long)
 */
	@Override
	OptionalInt apply(long value);
	/**
	 * Converts this {@code OptionalLongToIntFunction} to plain {@link LongToIntFunction} using default value.
	 * The returned {@link LongToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or return {@code result} if the {@link OptionalInt} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@link OptionalInt}
	 * @return plain {@link LongToIntFunction} that either unwraps {@link OptionalInt} or returns default value
	 * @see #orElseGet(IntSupplier)
	 * @see OptionalInt#orElse(int)
	 */
	default LongToIntFunction orElse(int result) {
		return new DefaultLongToIntFunction(this, result);
	}
	/**
	 * Converts this {@code OptionalLongToIntFunction} to plain {@link LongToIntFunction} using fallback {@link IntSupplier}.
	 * The returned {@link LongToIntFunction} will unwrap present value from the {@link OptionalInt} if possible,
	 * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
	 * 
	 * @param source
	 *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
	 * @return plain {@link LongToIntFunction} that either unwraps {@link OptionalInt} or falls back to {@code source}
	 * @see #orElse(int)
	 * @see OptionalInt#orElseGet(IntSupplier)
	 */
	default LongToIntFunction orElseGet(IntSupplier source) {
		return new FallbackLongToIntFunction(this, source);
	}
}
