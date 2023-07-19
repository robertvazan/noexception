// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalIntFunction} is typically obtained from {@link ExceptionHandler#fromIntFunction(IntFunction)},
 * in which case its return value is empty when the underlying {@link IntFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link IntFunction}
 * @see ExceptionHandler#fromIntFunction(IntFunction)
 * @see IntFunction
 */
@FunctionalInterface
public interface OptionalIntFunction<R> extends IntFunction<Optional<R>> {
    /**
     * Variation of {@link IntFunction#apply(int)} that returns {@link Optional}.
     * If this {@code OptionalIntFunction} is obtained from {@link ExceptionHandler#fromIntFunction(IntFunction)},
     * the {@link Optional} will be empty only if the underlying {@link IntFunction} throws.
     * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link IntFunction} (possibly {@code null}).
     * 
     * @param value
     *            see {@link IntFunction#apply(int)}
     * @return {@link Optional} typically wrapping return value of {@link IntFunction#apply(int)},
     *         or an empty {@link Optional} (typically signifying an exception)
     * @see ExceptionHandler#fromIntFunction(IntFunction)
     * @see IntFunction#apply(int)
     */
    @Override
    Optional<R> apply(int value);
    /**
     * Converts this {@code OptionalIntFunction} to plain {@link IntFunction} using default value.
     * The returned {@link IntFunction} will unwrap present value from the {@link Optional} if possible,
     * or return {@code result} if the {@link Optional} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link Optional}
     * @return plain {@link IntFunction} that either unwraps {@link Optional} or returns default value
     * @see #orElseGet(Supplier)
     * @see Optional#orElse(Object)
     */
    default IntFunction<R> orElse(R result) {
        return new DefaultIntFunction<R>(this, result);
    }
    /**
     * Converts this {@code OptionalIntFunction} to plain {@link IntFunction} using fallback {@link Supplier}.
     * The returned {@link IntFunction} will unwrap present value from the {@link Optional} if possible,
     * or fall back to calling {@code source} if the {@link Optional} is empty.
     * 
     * @param source
     *            {@link Supplier} to query for fallback value when {@link Optional} is empty
     * @return plain {@link IntFunction} that either unwraps {@link Optional} or falls back to {@code source}
     * @see #orElse(Object)
     * @see Optional#orElseGet(Supplier)
     */
    default IntFunction<R> orElseGet(Supplier<R> source) {
        return new FallbackIntFunction<R>(this, source);
    }
}
