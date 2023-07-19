// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Function} that returns {@link Optional} instead of the raw value.
 * {@code OptionalFunction} is typically obtained from {@link ExceptionHandler#function(Function)},
 * in which case its return value is empty when the underlying {@link Function} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Function}
 * @param <R>
 *            see {@link Function}
 * @see ExceptionHandler#function(Function)
 * @see Function
 */
@FunctionalInterface
public interface OptionalFunction<T, R> extends Function<T, Optional<R>> {
    /**
     * Variation of {@link Function#apply(Object)} that returns {@link Optional}.
     * If this {@code OptionalFunction} is obtained from {@link ExceptionHandler#function(Function)},
     * the {@link Optional} will be empty only if the underlying {@link Function} throws.
     * Otherwise the returned {@link Optional} just wraps the return value of underlying {@link Function} (possibly {@code null}).
     * 
     * @param t
     *            see {@link Function#apply(Object)}
     * @return {@link Optional} typically wrapping return value of {@link Function#apply(Object)},
     *         or an empty {@link Optional} (typically signifying an exception)
     * @see ExceptionHandler#function(Function)
     * @see Function#apply(Object)
     */
    @Override
    Optional<R> apply(T t);
    /**
     * Converts this {@code OptionalFunction} to plain {@link Function} using default value.
     * The returned {@link Function} will unwrap present value from the {@link Optional} if possible,
     * or return {@code result} if the {@link Optional} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link Optional}
     * @return plain {@link Function} that either unwraps {@link Optional} or returns default value
     * @see #orElseGet(Supplier)
     * @see Optional#orElse(Object)
     */
    default Function<T, R> orElse(R result) {
        return new DefaultFunction<T, R>(this, result);
    }
    /**
     * Converts this {@code OptionalFunction} to plain {@link Function} using fallback {@link Supplier}.
     * The returned {@link Function} will unwrap present value from the {@link Optional} if possible,
     * or fall back to calling {@code source} if the {@link Optional} is empty.
     * 
     * @param source
     *            {@link Supplier} to query for fallback value when {@link Optional} is empty
     * @return plain {@link Function} that either unwraps {@link Optional} or falls back to {@code source}
     * @see #orElse(Object)
     * @see Optional#orElseGet(Supplier)
     */
    default Function<T, R> orElseGet(Supplier<R> source) {
        return new FallbackFunction<T, R>(this, source);
    }
}
