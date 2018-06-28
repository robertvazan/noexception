// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link UnaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingUnaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromUnaryOperator(ThrowingUnaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link UnaryOperator}
 * @see CheckedExceptionHandler#fromUnaryOperator(ThrowingUnaryOperator)
 * @see UnaryOperator
 */
@FunctionalInterface public interface ThrowingUnaryOperator<T> extends ThrowingFunction<T, T> {
}
