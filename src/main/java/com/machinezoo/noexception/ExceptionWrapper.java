// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

final class ExceptionWrapper extends CheckedExceptionHandler {
	@Override public RuntimeException handle(Exception exception) {
		return new WrappedException(exception);
	}
}
