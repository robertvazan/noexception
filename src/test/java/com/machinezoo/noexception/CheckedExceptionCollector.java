// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.junit.Assert.*;
import java.util.*;

class CheckedExceptionCollector extends CheckedExceptionHandler {
	private final List<Throwable> collected = new ArrayList<>();
	public int size() {
		return collected.size();
	}
	public boolean empty() {
		return size() == 0;
	}
	public Throwable single() {
		assertEquals(1, size());
		return collected.get(0);
	}
	@Override public RuntimeException handle(Throwable exception) {
		collected.add(exception);
		return new CollectedException(exception);
	}
}
