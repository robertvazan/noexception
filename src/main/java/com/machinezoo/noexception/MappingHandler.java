// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;

final class MappingHandler extends CheckedExceptionHandler {
	private final Function<Exception, RuntimeException> wrapper;
	MappingHandler(Function<Exception, RuntimeException> wrapper) {
		Objects.requireNonNull(wrapper);
		this.wrapper = wrapper;
	}
	@Override public RuntimeException handle(Exception exception) {
		Objects.requireNonNull(exception);
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		return wrapper.apply(exception);
	}
}
