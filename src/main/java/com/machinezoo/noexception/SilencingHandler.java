// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

final class SilencingHandler extends ExceptionHandler {
	@Override
	public boolean handle(Throwable exception) {
		if (exception instanceof InterruptedException)
			Thread.currentThread().interrupt();
		return true;
	}
}
