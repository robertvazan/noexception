// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;
import org.slf4j.*;

final class ExceptionLogger extends ExceptionHandler {
	private final Logger logger;
	private final String message;
	ExceptionLogger(Logger logger, String message) {
		Objects.requireNonNull(logger);
		Objects.requireNonNull(message);
		this.logger = logger;
		this.message = message;
	}
	@Override public boolean handle(Throwable exception) {
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		logger.error(message, exception);
		return true;
	}
}
