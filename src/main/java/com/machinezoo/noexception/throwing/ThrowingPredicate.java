// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link Predicate} that allows throwing checked exceptions.
 * {@code ThrowingPredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#predicate(ThrowingPredicate)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Predicate}
 * @see CheckedExceptionHandler#predicate(ThrowingPredicate)
 * @see Predicate
 */
@FunctionalInterface public interface ThrowingPredicate<T> {
	/**
	 * Variation of {@link Predicate#test(Object)} that allows throwing checked exceptions.
	 * 
	 * @param t
	 *            see {@link Predicate#test(Object)}
	 * @return see {@link Predicate#test(Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#predicate(ThrowingPredicate)
	 * @see Predicate#test(Object)
	 */
	boolean test(T t) throws Exception;
}
