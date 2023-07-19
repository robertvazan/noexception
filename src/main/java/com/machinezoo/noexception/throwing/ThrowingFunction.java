// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Function} that allows throwing checked exceptions.
 * {@code ThrowingFunction} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#function(ThrowingFunction)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Function}
 * @param <R>
 *            see {@link Function}
 * @see CheckedExceptionHandler#function(ThrowingFunction)
 * @see Function
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {
    /**
     * Variation of {@link Function#apply(Object)} that allows throwing checked exceptions.
     * 
     * @param t
     *            see {@link Function#apply(Object)}
     * @return see {@link Function#apply(Object)}
     * @throws Throwable
     *             if unable to complete
     * @see CheckedExceptionHandler#function(ThrowingFunction)
     * @see Function#apply(Object)
     */
    R apply(T t) throws Throwable;
}
