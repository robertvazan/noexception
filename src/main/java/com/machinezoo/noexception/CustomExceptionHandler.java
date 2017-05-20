package com.machinezoo.noexception;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class CustomExceptionHandler extends ExceptionHandler {
	private final Predicate<Throwable> handler;
	@Override public boolean handle(Throwable exception) {
		return handler.test(exception);
	}
}