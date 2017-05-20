package com.machinezoo.noexception;

final class ExceptionPassThrough extends ExceptionHandler {
	@Override public boolean handle(Throwable exception) {
		return false;
	}
}