// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongConsumer} that allows throwing checked exceptions.
 * {@code ThrowingLongConsumer} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongConsumer(ThrowingLongConsumer)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongConsumer(ThrowingLongConsumer)
 * @see LongConsumer
 */
@FunctionalInterface
public interface ThrowingLongConsumer {
    /**
     * Variation of {@link LongConsumer#accept(long)} that allows throwing checked exceptions.
     * 
     * @param value
     *            see {@link LongConsumer#accept(long)}
     * @throws Throwable
     *             if unable to complete
     * @see CheckedExceptionHandler#fromLongConsumer(ThrowingLongConsumer)
     * @see LongConsumer#accept(long)
     */
    void accept(long value) throws Throwable;
}
