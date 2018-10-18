// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BinaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingBinaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBinaryOperator(ThrowingBinaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BinaryOperator}
 * @see CheckedExceptionHandler#fromBinaryOperator(ThrowingBinaryOperator)
 * @see BinaryOperator
 */
@FunctionalInterface public interface ThrowingBinaryOperator<T> extends ThrowingBiFunction<T, T, T> {
}
