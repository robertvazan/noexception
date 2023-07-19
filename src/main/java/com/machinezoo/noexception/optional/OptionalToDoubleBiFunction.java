// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link ToDoubleBiFunction} that returns {@link OptionalDouble} instead of the raw value.
 * {@code OptionalToDoubleBiFunction} is typically obtained from {@link ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)},
 * in which case its return value is empty when the underlying {@link ToDoubleBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToDoubleBiFunction}
 * @param <U>
 *            see {@link ToDoubleBiFunction}
 * @see ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)
 * @see ToDoubleBiFunction
 */
@FunctionalInterface
public interface OptionalToDoubleBiFunction<T, U> extends BiFunction<T, U, OptionalDouble> {
    /**
     * Variation of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)} that returns {@link OptionalDouble}.
     * If this {@code OptionalToDoubleBiFunction} is obtained from {@link ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)},
     * the {@link OptionalDouble} will be empty only if the underlying {@link ToDoubleBiFunction} throws.
     * Otherwise the returned {@link OptionalDouble} just wraps the return value of underlying {@link ToDoubleBiFunction}.
     * 
     * @param t
     *            see {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
     * @param u
     *            see {@link ToDoubleBiFunction#applyAsDouble(Object, Object)}
     * @return {@link OptionalDouble} typically wrapping return value of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)},
     *         or an empty {@link OptionalDouble} (typically signifying an exception)
     * @see ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)
     * @see ToDoubleBiFunction#applyAsDouble(Object, Object)
     */
    @Override
    OptionalDouble apply(T t, U u);
    /**
     * Converts this {@code OptionalToDoubleBiFunction} to plain {@link ToDoubleBiFunction} using default value.
     * The returned {@link ToDoubleBiFunction} will unwrap present value from the {@link OptionalDouble} if possible,
     * or return {@code result} if the {@link OptionalDouble} is empty.
     * 
     * @param result
     *            default value to return instead of an empty {@link OptionalDouble}
     * @return plain {@link ToDoubleBiFunction} that either unwraps {@link OptionalDouble} or returns default value
     * @see #orElseGet(DoubleSupplier)
     * @see OptionalDouble#orElse(double)
     */
    default ToDoubleBiFunction<T, U> orElse(double result) {
        return new DefaultToDoubleBiFunction<T, U>(this, result);
    }
    /**
     * Converts this {@code OptionalToDoubleBiFunction} to plain {@link ToDoubleBiFunction} using fallback {@link DoubleSupplier}.
     * The returned {@link ToDoubleBiFunction} will unwrap present value from the {@link OptionalDouble} if possible,
     * or fall back to calling {@code source} if the {@link OptionalDouble} is empty.
     * 
     * @param source
     *            {@link DoubleSupplier} to query for fallback value when {@link OptionalDouble} is empty
     * @return plain {@link ToDoubleBiFunction} that either unwraps {@link OptionalDouble} or falls back to {@code source}
     * @see #orElse(double)
     * @see OptionalDouble#orElseGet(DoubleSupplier)
     */
    default ToDoubleBiFunction<T, U> orElseGet(DoubleSupplier source) {
        return new FallbackToDoubleBiFunction<T, U>(this, source);
    }
}
