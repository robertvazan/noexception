package com.machinezoo.noexception;

import static org.junit.Assert.*;
import java.util.*;
import lombok.*;

@RequiredArgsConstructor class ExceptionCollector extends ExceptionHandler {
	private final boolean swallow;
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
	@Override public boolean handle(Throwable exception) {
		collected.add(exception);
		return swallow;
	}
}