package com.machinezoo.noexception;

import org.slf4j.*;

final class ExceptionLogger extends ExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);
	@Override public boolean handle(Throwable exception) {
		logger.error("Caught exception", exception);
		return true;
	}
}