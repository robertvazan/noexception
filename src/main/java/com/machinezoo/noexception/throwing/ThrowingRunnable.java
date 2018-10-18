// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import com.machinezoo.noexception.*;

/**
 * Variation of {@link Runnable} that allows throwing checked exceptions.
 * {@code ThrowingRunnable} is usually implemented by a lambda
 * and passed to {@link CheckedExceptionHandler#runnable(ThrowingRunnable)}.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * 
 * @see CheckedExceptionHandler#runnable(ThrowingRunnable)
 * @see Runnable
 */
@FunctionalInterface public interface ThrowingRunnable {
	/**
	 * Variation of {@link Runnable#run()} that allows throwing checked exceptions.
	 * 
	 * @throws Exception
	 *             if unable to complete
	 * @see CheckedExceptionHandler#runnable(ThrowingRunnable)
	 * @see Runnable#run()
	 */
	void run() throws Exception;
}
