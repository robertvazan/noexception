// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link DoublePredicate} that allows throwing checked exceptions.
 * {@code ThrowingDoublePredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromDoublePredicate(ThrowingDoublePredicate)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromDoublePredicate(ThrowingDoublePredicate)
 * @see DoublePredicate
 */
@FunctionalInterface public interface ThrowingDoublePredicate {
	/**
	 * Variation of {@link DoublePredicate#test(double)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link DoublePredicate#test(double)}
	 * @return see {@link DoublePredicate#test(double)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromDoublePredicate(ThrowingDoublePredicate)
	 * @see DoublePredicate#test(double)
	 */
	boolean test(double value) throws Exception;
}
