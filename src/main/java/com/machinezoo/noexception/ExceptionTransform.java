// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends CheckedExceptionHandler {
	private final Function<Exception, RuntimeException> wrapper;
	@Override public RuntimeException handle(Exception exception) {
		return wrapper.apply(exception);
	}
}
