package com.machinezoo.noexception;

final class ExceptionWrapper extends CheckedExceptionHandler {
	@Override public void handle(Throwable exception) {
		if (exception instanceof RuntimeException)
			throw (RuntimeException)exception;
		if (exception instanceof Error)
			throw (Error)exception;
		throw new WrappedException(exception);
	}
}