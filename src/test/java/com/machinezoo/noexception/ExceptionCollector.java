// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.junit.Assert.*;
import java.util.*;

class ExceptionCollector extends ExceptionHandler {
	private final boolean swallow;
	private final List<Throwable> collected = new ArrayList<>();
	ExceptionCollector(boolean swallow) {
		this.swallow = swallow;
	}
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
	@Override public boolean handle(Throwable exception) {
		collected.add(exception);
		return swallow;
	}
}