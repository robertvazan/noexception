// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends CheckedExceptionHandler {
	private final Function<Exception, RuntimeException> wrapper;
	@Override public RuntimeException handle(Throwable exception) {
		if (exception instanceof RuntimeException)
			return (RuntimeException)exception;
		if (exception instanceof Error)
			throw (Error)exception;
		if (exception instanceof Exception)
			return wrapper.apply((Exception)exception);
		return new WrappedException(exception);
	}
}
