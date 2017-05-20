// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

public class WrappedException extends RuntimeException {
	private static final long serialVersionUID = -1535521026317618861L;
	public WrappedException(Throwable cause) {
		super(cause);
	}
}
