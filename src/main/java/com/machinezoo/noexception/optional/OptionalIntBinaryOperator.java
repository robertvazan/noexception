// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntBinaryOperator} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalIntBinaryOperator} is typically obtained from {@link ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)},
 * in which case its return value is empty when the underlying {@link IntBinaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)
 * @see IntBinaryOperator
 */
@FunctionalInterface
public interface OptionalIntBinaryOperator {
    /**
     * Variation of {@link IntBinaryOperator#applyAsInt(int, int)} that returns {@link OptionalInt}.
     * If this {@code OptionalIntBinaryOperator} is obtained from {@link ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)},
     * the {@link OptionalInt} will be empty only if the underlying {@link IntBinaryOperator} throws.
     * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link IntBinaryOperator}.
     * 
     * @param left
     *            see {@link IntBinaryOperator#applyAsInt(int, int)}
     * @param right
     *            see {@link IntBinaryOperator#applyAsInt(int, int)}
     * @return {@link OptionalInt} typically wrapping return value of {@link IntBinaryOperator#applyAsInt(int, int)},
     *         or an empty {@link OptionalInt} (typically signifying an exception)
     * @see ExceptionHandler#fromIntBinaryOperator(IntBinaryOperator)
     * @see IntBinaryOperator#applyAsInt(int, int)
     */
    OptionalInt apply(int left, int right);
    /**
     * Converts this {@code OptionalIntBinaryOperator} to plain {@link IntBinaryOperator} using default value.
     * The returned {@link IntBinaryOperator} will unwrap present value from the {@link OptionalInt} if possible,
     * or return {@code result} if the {@link OptionalInt} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalInt}
     * @return plain {@link IntBinaryOperator} that either unwraps {@link OptionalInt} or returns default value
     * @see #orElseGet(IntSupplier)
     * @see OptionalInt#orElse(int)
     */
    default IntBinaryOperator orElse(int result) {
        return new DefaultIntBinaryOperator(this, result);
    }
    /**
     * Converts this {@code OptionalIntBinaryOperator} to plain {@link IntBinaryOperator} using fallback {@link IntSupplier}.
     * The returned {@link IntBinaryOperator} will unwrap present value from the {@link OptionalInt} if possible,
     * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
     * 
     * @param source
     *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
     * @return plain {@link IntBinaryOperator} that either unwraps {@link OptionalInt} or falls back to {@code source}
     * @see #orElse(int)
     * @see OptionalInt#orElseGet(IntSupplier)
     */
    default IntBinaryOperator orElseGet(IntSupplier source) {
        return new FallbackIntBinaryOperator(this, source);
    }
}
