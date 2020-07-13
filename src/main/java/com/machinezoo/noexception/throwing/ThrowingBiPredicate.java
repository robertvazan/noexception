// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link BiPredicate} that allows throwing checked exceptions.
 * {@code ThrowingBiPredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromBiPredicate(ThrowingBiPredicate)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link BiPredicate}
 * @param <U>
 *            see {@link BiPredicate}
 * @see CheckedExceptionHandler#fromBiPredicate(ThrowingBiPredicate)
 * @see BiPredicate
 */
@FunctionalInterface
public interface ThrowingBiPredicate<T, U> {
	/**
	 * Variation of {@link BiPredicate#test(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link BiPredicate#test(Object, Object)}
	 * @param u
	 *            see {@link BiPredicate#test(Object, Object)}
	 * @return see {@link BiPredicate#test(Object, Object)}
	 * @throws Throwable
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromBiPredicate(ThrowingBiPredicate)
	 * @see BiPredicate#test(Object, Object)
	 */
	boolean test(T t, U u) throws Throwable;
}
