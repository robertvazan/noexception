// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

final class ExceptionSmuggler extends CheckedExceptionHandler {
	@Override @SneakyThrows public RuntimeException handle(@NonNull Exception exception) {
		throw exception;
	}
}
