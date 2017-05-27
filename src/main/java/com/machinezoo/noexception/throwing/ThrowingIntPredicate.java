// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link IntPredicate} that allows throwing checked exceptions.
 * {@code ThrowingIntPredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromIntPredicate(ThrowingIntPredicate)}.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromIntPredicate(ThrowingIntPredicate)
 * @see IntPredicate
 */
@FunctionalInterface public interface ThrowingIntPredicate {
	/**
	 * Variation of {@link IntPredicate#test(int)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            See {@link IntPredicate#test(int)}
	 * @return See {@link IntPredicate#test(int)}
	 * @throws Exception
	 *             Whenever necessary.
	 * @see CheckedExceptionHandler#fromIntPredicate(ThrowingIntPredicate)
	 * @see IntPredicate#test(int)
	 */
	boolean test(int value) throws Exception;
}
