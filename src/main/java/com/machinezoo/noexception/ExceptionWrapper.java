// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

final class ExceptionWrapper extends CheckedExceptionHandler {
	@Override public RuntimeException handle(Throwable exception) {
		if (exception instanceof RuntimeException)
			return (RuntimeException)exception;
		if (exception instanceof Error)
			throw (Error)exception;
		return new WrappedException(exception);
	}
}
