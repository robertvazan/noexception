// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

final class ExceptionSmuggler extends CheckedExceptionHandler {
	@Override @SneakyThrows public void handle(Throwable exception) {
		throw exception;
	}
}
