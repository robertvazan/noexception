// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoubleUnaryOperator} that allows throwing checked exceptions.
 * {@code ThrowingDoubleUnaryOperator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator)
 * @see DoubleUnaryOperator
 */
@FunctionalInterface
public interface ThrowingDoubleUnaryOperator {
    /**
     * Variation of {@link DoubleUnaryOperator#applyAsDouble(double)} that allows throwing checked exceptions.
     * 
     * @param operand
     *            see {@link DoubleUnaryOperator#applyAsDouble(double)}
     * @return see {@link DoubleUnaryOperator#applyAsDouble(double)}
     * @throws Throwable
     *             if unable to complete
     * @see CheckedExceptionHandler#fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator)
     * @see DoubleUnaryOperator#applyAsDouble(double)
     */
    double applyAsDouble(double operand) throws Throwable;
}
