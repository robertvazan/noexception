// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class FilteredExceptionCollector extends ExceptionFilter {
	private final List<Throwable> collected = new ArrayList<>();
	private final boolean replace;
	FilteredExceptionCollector(boolean replace) {
		this.replace = replace;
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
	@Override public void handle(Throwable exception) {
		collected.add(exception);
		if (replace)
			throw new CollectedException(exception);
	}
}
