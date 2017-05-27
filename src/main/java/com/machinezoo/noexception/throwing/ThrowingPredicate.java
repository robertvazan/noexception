// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Predicate} that allows throwing checked exceptions.
 * {@code ThrowingPredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#predicate(ThrowingPredicate)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @param <T>
 *            See {@link Predicate}.
 * @see CheckedExceptionHandler#predicate(ThrowingPredicate)
 * @see Predicate
 */
@FunctionalInterface public interface ThrowingPredicate<T> {
	/**
	 * Variation of {@link Predicate#test(Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            See {@link Predicate#test(Object)}
	 * @return See {@link Predicate#test(Object)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#predicate(ThrowingPredicate)
	 * @see Predicate#test(Object)
	 */
	boolean test(T t) throws Exception;
}
