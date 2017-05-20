package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionTransform extends CheckedExceptionHandler {
	private final Function<Throwable, RuntimeException> wrapper;
	@Override public void handle(Throwable exception) {
		throw wrapper.apply(exception);
	}
}