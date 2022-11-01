// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import org.slf4j.*;

@Deprecated
final class LoggingHandler extends ExceptionHandler {
	private final Logger logger;
	private final Supplier<String> supplier;
	LoggingHandler(Logger logger, Supplier<String> supplier) {
		Objects.requireNonNull(logger);
		Objects.requireNonNull(supplier);
		this.logger = logger;
		this.supplier = supplier;
	}
	@Override
	public boolean handle(Throwable exception) {
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		String message = null;
		try {
			message = supplier.get();
		} catch (Throwable ex) {
			logger.error("Failed to produce log message.", ex);
		}
		if (message == null)
			message = "Caught exception.";
		logger.error(message, exception);
		return true;
	}
}
