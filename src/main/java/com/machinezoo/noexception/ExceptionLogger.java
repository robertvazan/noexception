// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import org.slf4j.*;
import lombok.*;

@RequiredArgsConstructor final class ExceptionLogger extends ExceptionHandler {
	private final Logger logger;
	private final String message;
	@Override public boolean handle(Throwable exception) {
		logger.error(message, exception);
		return true;
	}
}
