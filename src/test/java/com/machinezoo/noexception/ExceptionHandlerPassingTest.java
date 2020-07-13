// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class ExceptionHandlerPassingTest {
	@Test
	public void complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		Runnable lambda = mock(Runnable.class);
		collector.passing().run(lambda);
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test
	public void rethrow() {
		ExceptionCollector collector = new ExceptionCollector(true);
		assertThrows(NumberFormatException.class, () -> collector.passing().runnable(() -> {
			throw new NumberFormatException();
		}).run());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	static class ReplacingHandler extends ExceptionHandler {
		@Override
		public boolean handle(Throwable exception) {
			throw new CollectedException(exception);
		}
	}
	@Test
	public void propagate() {
		ReplacingHandler handler = new ReplacingHandler();
		assertThrows(CollectedException.class, () -> handler.passing().runnable(() -> {
			throw new NumberFormatException();
		}).run());
	}
}
