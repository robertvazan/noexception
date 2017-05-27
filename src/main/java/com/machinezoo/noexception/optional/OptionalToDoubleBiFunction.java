// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * Variation of {@link ToDoubleBiFunction} that returns {@code OptionalDouble} instead of the raw value.
 * {@code OptionalToDoubleBiFunction} is typically obtained from {@link ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)},
 * in which case its return value is empty when the underlying {@code ToDoubleBiFunction} throws an exception.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            see {@link ToDoubleBiFunction}
 * @param <U>
 *            see {@link ToDoubleBiFunction}
 * @see ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)
 * @see ToDoubleBiFunction
 */
@FunctionalInterface public interface OptionalToDoubleBiFunction<T, U> extends BiFunction<T, U, OptionalDouble> {
	/**
	 * Variation of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)} that returns {@code OptionalDouble}.
	 * If this {@code OptionalToDoubleBiFunction} is obtained from {@link ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)},
	 * the {@code OptionalDouble} will be empty only if the underlying {@code ToDoubleBiFunction} throws.
	 * Otherwise the returned {@code OptionalDouble} just wraps the return value of underlying {@code ToDoubleBiFunction}.
	 * 
	 * @param t,
	 *            see {@link ToDoubleBiFunction#apply(Object, Object)}
	 * @param u
	 *            see {@link ToDoubleBiFunction#apply(Object, Object)}
	 * @return {@code OptionalDouble} typically wrapping return value of {@link ToDoubleBiFunction#applyAsDouble(Object, Object)},
	 *         or an empty {@code OptionalDouble} (typically signifying an exception)
	 * @see ExceptionHandler#fromToDoubleBiFunction(ToDoubleBiFunction)
	 * @see ToDoubleBiFunction#applyAsDouble(Object, Object)
	 */
	@Override OptionalDouble apply(T t, U u);
	/**
	 * Convert this {@code OptionalToDoubleBiFunction} to plain {@code ToDoubleBiFunction} using default value.
	 * The returned {@code ToDoubleBiFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or return {@code result} if the {@code OptionalDouble} is empty.
	 * 
	 * @param result
	 *            default value to return instead of an empty {@code OptionalDouble}
	 * @return plain {@code ToDoubleBiFunction} that either unwraps {@code OptionalDouble} or returns default value
	 * @see #orElseGet(DoubleSupplier)
	 * @see OptionalDouble#orElse(double)
	 */
	default ToDoubleBiFunction<T, U> orElse(double result) {
		return new DefaultToDoubleBiFunction<T, U>(this, result);
	}
	/**
	 * Convert this {@code OptionalToDoubleBiFunction} to plain {@code ToDoubleBiFunction} using fallback {@code DoubleSupplier}.
	 * The returned {@code ToDoubleBiFunction} will unwrap present value from {@code OptionalDouble} if possible,
	 * or fall back to calling {@code source} if the {@code OptionalDouble} is empty.
	 * 
	 * @param source
	 *            {@code DoubleSupplier} to query for fallback value when {@code OptionalDouble} is empty
	 * @return plain {@code ToDoubleBiFunction} that either unwraps {@code OptionalDouble} or falls back to {@code source}
	 * @see #orElse(double)
	 * @see OptionalDouble#orElseGet(DoubleSupplier)
	 */
	default ToDoubleBiFunction<T, U> orElseGet(DoubleSupplier source) {
		return new FallbackToDoubleBiFunction<T, U>(this, source);
	}
}
