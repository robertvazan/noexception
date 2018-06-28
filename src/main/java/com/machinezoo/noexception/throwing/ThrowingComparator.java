// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import com.machinezoo.noexception.*;

/**
 * Variation of {@link Comparator} that allows throwing checked exceptions.
 * {@code ThrowingComparator} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#comparator(ThrowingComparator)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @param <T>
 *            see {@link Comparator}
 * @see CheckedExceptionHandler#comparator(ThrowingComparator)
 * @see Comparator
 */
@FunctionalInterface public interface ThrowingComparator<T> {
	/**
	 * Variation of {@link Comparator#compare(Object, Object)} that allows throwing checked exceptions.
	 * 
	 * @param left
	 *            see {@link Comparator#compare(Object, Object)}
	 * @param right
	 *            see {@link Comparator#compare(Object, Object)}
	 * @return see {@link Comparator#compare(Object, Object)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#comparator(ThrowingComparator)
	 * @see Comparator#compare(Object, Object)
	 */
	int compare(T left, T right) throws Exception;
}
