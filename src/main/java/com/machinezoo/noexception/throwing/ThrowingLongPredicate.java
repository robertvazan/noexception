// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.util.function.*;
import com.machinezoo.noexception.*;

/**
 * Variation of {@link LongPredicate} that allows throwing checked exceptions.
 * {@code ThrowingLongPredicate} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#fromLongPredicate(ThrowingLongPredicate)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#fromLongPredicate(ThrowingLongPredicate)
 * @see LongPredicate
 */
@FunctionalInterface public interface ThrowingLongPredicate {
	/**
	 * Variation of {@link LongPredicate#test(long)} that allows throwing checked exceptions.
	 * 
	 * @param value
	 *            see {@link LongPredicate#test(long)}
	 * @return see {@link LongPredicate#test(long)}
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#fromLongPredicate(ThrowingLongPredicate)
	 * @see LongPredicate#test(long)
	 */
	boolean test(long value) throws Exception;
}
