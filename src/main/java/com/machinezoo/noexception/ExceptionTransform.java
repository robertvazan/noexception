package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends CheckedExceptionHandler {
	private final Function<Exception, RuntimeException> wrapper;
	@Override public void handle(Throwable exception) {
		if (exception instanceof RuntimeException)
			throw (RuntimeException)exception;
		if (exception instanceof Error)
			throw (Error)exception;
		if (exception instanceof Exception)
			throw wrapper.apply((Exception)exception);
		throw new WrappedException(exception);
	}
}