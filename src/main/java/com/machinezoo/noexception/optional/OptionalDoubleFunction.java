// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleFunction} that returns {@link Optional} instead of the raw value.
 * {@code OptionalDoubleFunction} is typically obtained from {@link ExceptionHandler#fromDoubleFunction(DoubleFunction)},
 * in which case its return value is empty when the underlying {@link DoubleFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <R>
 *            see {@link DoubleFunction}
 * @see ExceptionHandler#fromDoubleFunction(DoubleFunction)
 * @see DoubleFunction
 */
@FunctionalInterface
public interface OptionalDoubleFunction<R> extends DoubleFunction<Optional<R>> {
    /**
     * Variation of {@link DoubleFunction#apply(double)} that returns {@link Optional}.
     * If this {@code OptionalDoubleFunction} is obtained from {@link ExceptionHandler#fromDoubleFunction(DoubleFunction)},
     * the {@link Optional} will be empty only if the underlying {@link DoubleFunction} throws.
     * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link DoubleFunction} (possibly {@code null}).
     * 
     * @param value
     *            see {@link DoubleFunction#apply(double)}
     * @return {@link Optional} typically wrapping return value of {@link DoubleFunction#apply(double)},
     *         or an empty {@link Optional} (typically signifying an exception)
     * @see ExceptionHandler#fromDoubleFunction(DoubleFunction)
     * @see DoubleFunction#apply(double)
     */
    @Override
    Optional<R> apply(double value);
    /**
     * Converts this {@code OptionalDoubleFunction} to plain {@link DoubleFunction} using default value.
     * The returned {@link DoubleFunction} will unwrap present value from the {@link Optional} if possible,
     * or return {@code result} if the {@link Optional} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link Optional}
     * @return plain {@link DoubleFunction} that either unwraps {@link Optional} or returns default value
     * @see #orElseGet(Supplier)
     * @see Optional#orElse(Object)
     */
    default DoubleFunction<R> orElse(R result) {
        return new DefaultDoubleFunction<R>(this, result);
    }
    /**
     * Converts this {@code OptionalDoubleFunction} to plain {@link DoubleFunction} using fallback {@link Supplier}.
     * The returned {@link DoubleFunction} will unwrap present value from the {@link Optional} if possible,
     * or fall back to calling {@code source} if the {@link Optional} is empty.
     * 
     * @param source
     *            {@link Supplier} to query for fallback value when {@link Optional} is empty
     * @return plain {@link DoubleFunction} that either unwraps {@link Optional} or falls back to {@code source}
     * @see #orElse(Object)
     * @see Optional#orElseGet(Supplier)
     */
    default DoubleFunction<R> orElseGet(Supplier<R> source) {
        return new FallbackDoubleFunction<R>(this, source);
    }
}
