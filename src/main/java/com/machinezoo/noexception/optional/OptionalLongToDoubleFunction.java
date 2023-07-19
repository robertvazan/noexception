// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToDoubleFunction} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalLongToDoubleFunction} is typically obtained from {@link ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)},
 * in which case its return value is empty when the underlying {@link LongToDoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)
 * @see LongToDoubleFunction
 */
@FunctionalInterface
public interface OptionalLongToDoubleFunction extends LongFunction<OptionalDouble> {
    /**
     * Variation of {@link LongToDoubleFunction#applyAsDouble(long)} that returns {@link OptionalDouble}.
     * If this {@code OptionalLongToDoubleFunction} is obtained from {@link ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)},
     * the {@link OptionalDouble} will be empty only if the underlying {@link LongToDoubleFunction} throws.
     * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link LongToDoubleFunction}.
     * 
     * @param value
     *            see {@link LongToDoubleFunction#applyAsDouble(long)}
     * @return {@link OptionalDouble} typically wrapping return value of {@link LongToDoubleFunction#applyAsDouble(long)},
     *         or an empty {@link OptionalDouble} (typically signifying an exception)
     * @see ExceptionHandler#fromLongToDoubleFunction(LongToDoubleFunction)
     * @see LongToDoubleFunction#applyAsDouble(long)
     */
    @Override
    OptionalDouble apply(long value);
    /**
     * Converts this {@code OptionalLongToDoubleFunction} to plain {@link LongToDoubleFunction} using default value.
     * The returned {@link LongToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
     * or return {@code result} if the {@link OptionalDouble} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalDouble}
     * @return plain {@link LongToDoubleFunction} that either unwraps {@link OptionalDouble} or returns default value
     * @see #orElseGet(DoubleSupplier)
     * @see OptionalDouble#orElse(double)
     */
    default LongToDoubleFunction orElse(double result) {
        return new DefaultLongToDoubleFunction(this, result);
    }
    /**
     * Converts this {@code OptionalLongToDoubleFunction} to plain {@link LongToDoubleFunction} using fallback {@link DoubleSupplier}.
     * The returned {@link LongToDoubleFunction} will unwrap present value from the {@link OptionalDouble} if possible,
     * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
     * 
     * @param source
     *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
     * @return plain {@link LongToDoubleFunction} that either unwraps {@link OptionalDouble} or falls back to {@code source}
     * @see #orElse(double)
     * @see OptionalDouble#orElseGet(DoubleSupplier)
     */
    default LongToDoubleFunction orElseGet(DoubleSupplier source) {
        return new FallbackLongToDoubleFunction(this, source);
    }
}
