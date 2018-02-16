// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

final class ExceptionSmuggler extends CheckedExceptionHandler {
	@Override public RuntimeException handle(Exception exception) {
		ExceptionSmuggler.<RuntimeException>rethrow(exception);
		return new IllegalStateException();
	}
	@SuppressWarnings("unchecked") static <T extends Throwable> void rethrow(Throwable exception) throws T {
		throw (T)exception;
	}
}
