// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

final class SneakingHandler extends CheckedExceptionHandler {
	@Override public RuntimeException handle(Exception exception) {
		return sneak(exception);
	}
	@SuppressWarnings("unchecked") static <T extends Throwable> RuntimeException sneak(Throwable exception) throws T {
		throw (T)exception;
	}
}
