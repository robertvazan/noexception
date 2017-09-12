// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import lombok.*;

class ExceptionWrapper extends CheckedExceptionHandler {
	protected RuntimeException wrap(Exception exception) {
		return new WrappedException(exception);
	}

	@Override public final RuntimeException handle(@NonNull Exception exception) {
		// Because of we catched InterruptedException, we must restore interrupt status
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		return wrap(exception);
	}
}
