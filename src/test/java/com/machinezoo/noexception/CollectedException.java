package com.machinezoo.noexception;

class CollectedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	CollectedException(Throwable cause) {
		super(cause);
	}
}
