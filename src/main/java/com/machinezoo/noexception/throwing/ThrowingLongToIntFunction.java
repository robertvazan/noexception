// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongToIntFunction} that allows throwing checked exceptions.
 * {@code ThrowingLongToIntFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)
 * @see LongToIntFunction
 */
@FunctionalInterface
public interface ThrowingLongToIntFunction {
    /**
     * Variation of {@link LongToIntFunction#applyAsInt(long)} that allows throwing checked exceptions.
     * 
     * @param value
     *            see {@link LongToIntFunction#applyAsInt(long)}
     * @return see {@link LongToIntFunction#applyAsInt(long)}
     * @throws Throwable
     *             if unable to complete
     * @see CheckedExceptionHandler#fromLongToIntFunction(ThrowingLongToIntFunction)
     * @see LongToIntFunction#applyAsInt(long)
     */
    int applyAsInt(long value) throws Throwable;
}
