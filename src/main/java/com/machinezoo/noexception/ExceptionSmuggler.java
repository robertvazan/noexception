// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

final class ExceptionSmuggler extends CheckedExceptionHandler {
	@Override @SneakyThrows public RuntimeException handle(Throwable exception) {
		if (exception instanceof RuntimeException)
			return (RuntimeException)exception;
		if (exception instanceof Error)
			throw (Error)exception;
		throw exception;
	}
}
