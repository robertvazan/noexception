// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

/**
 * General-purpose unchecked wrapper exception.
 * It is thrown by exception handler returned from {@link Exceptions#wrap()},
 * but it can be created directly for any purpose.
 * Wrapped checked exception can be retrieved using {@link #getCause()} method.
 * 
 * @see Exceptions#wrap()
 */
public class WrappedException extends RuntimeException {
	private static final long serialVersionUID = -1535521026317618861L;
	/**
	 * Wrap checked exception.
	 * Wrapped checked exception can be later retrieved using {@link #getCause()} method.
	 * 
	 * @param cause
	 *            checked exception to be wrapped
	 * @see Exceptions#wrap()
	 */
	public WrappedException(Throwable cause) {
		super(cause);
	}
}
