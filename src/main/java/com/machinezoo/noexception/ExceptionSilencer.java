package com.machinezoo.noexception;

final class ExceptionSilencer extends ExceptionHandler {
	@Override public boolean handle(Throwable exception) {
		return true;
	}
}