// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;

final class WrappingHandler extends CheckedExceptionHandler {
	@Override
	public RuntimeException handle(Exception exception) {
		Objects.requireNonNull(exception);
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		return new WrappedException(exception);
	}
}
