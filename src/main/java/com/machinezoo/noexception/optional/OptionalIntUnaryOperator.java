// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntUnaryOperator} that returns {@link OptionalInt} instead of the raw value.
 * {@code OptionalIntUnaryOperator} is typically obtained from {@link ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)},
 * in which case its return value is empty when the underlying {@link IntUnaryOperator} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)
 * @see IntUnaryOperator
 */
@FunctionalInterface
public interface OptionalIntUnaryOperator extends IntFunction<OptionalInt> {
    /**
     * Variation of {@link IntUnaryOperator#applyAsInt(int)} that returns {@link OptionalInt}.
     * If this {@code OptionalIntUnaryOperator} is obtained from {@link ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)},
     * the {@link OptionalInt} will be empty only if the underlying {@link IntUnaryOperator} throws.
     * Otherwise the returned {@link OptionalInt} just wraps the return value of underlying {@link IntUnaryOperator}.
     * 
     * @param operand
     *            see {@link IntUnaryOperator#applyAsInt(int)}
     * @return {@link OptionalInt} typically wrapping return value of {@link IntUnaryOperator#applyAsInt(int)},
     *         or an empty {@link OptionalInt} (typically signifying an exception)
     * @see ExceptionHandler#fromIntUnaryOperator(IntUnaryOperator)
     * @see IntUnaryOperator#applyAsInt(int)
     */
    @Override
    OptionalInt apply(int operand);
    /**
     * Converts this {@code OptionalIntUnaryOperator} to plain {@link IntUnaryOperator} using default value.
     * The returned {@link IntUnaryOperator} will unwrap present value from the {@link OptionalInt} if possible,
     * or return {@code result} if the {@link OptionalInt} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalInt}
     * @return plain {@link IntUnaryOperator} that either unwraps {@link OptionalInt} or returns default value
     * @see #orElseGet(IntSupplier)
     * @see OptionalInt#orElse(int)
     */
    default IntUnaryOperator orElse(int result) {
        return new DefaultIntUnaryOperator(this, result);
    }
    /**
     * Converts this {@code OptionalIntUnaryOperator} to plain {@link IntUnaryOperator} using fallback {@link IntSupplier}.
     * The returned {@link IntUnaryOperator} will unwrap present value from the {@link OptionalInt} if possible,
     * or fall back to calling {@code source} if the {@link OptionalInt} is empty.
     * 
     * @param source
     *            {@link IntSupplier} to query for fallback value when {@link OptionalInt} is empty
     * @return plain {@link IntUnaryOperator} that either unwraps {@link OptionalInt} or falls back to {@code source}
     * @see #orElse(int)
     * @see OptionalInt#orElseGet(IntSupplier)
     */
    default IntUnaryOperator orElseGet(IntSupplier source) {
        return new FallbackIntUnaryOperator(this, source);
    }
}
