// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

final class ExceptionWrapper extends CheckedExceptionHandler {
	@Override public RuntimeException handle(@NonNull Exception exception) {
		return new WrappedException(exception);
	}
}
