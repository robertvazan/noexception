// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

final class ExceptionWrapper extends CheckedExceptionHandler {
	@Override public RuntimeException handle(@NonNull Exception exception) {
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		return new WrappedException(exception);
	}
}
