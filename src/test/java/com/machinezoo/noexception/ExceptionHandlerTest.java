package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;
import lombok.*;

public class ExceptionHandlerTest {
	@Test public void runnable_complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		Runnable lambda = mock(Runnable.class);
		collector.runnable(lambda).run();
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test public void runnable_swallowException() {
		ExceptionCollector collector = new ExceptionCollector(true);
		collector.runnable(() -> {
			throw new NumberFormatException();
		}).run();
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void runnable_passException() {
		ExceptionCollector collector = new ExceptionCollector(false);
		try {
			collector.runnable(() -> {
				throw new NumberFormatException();
			}).run();
			fail();
		} catch (NumberFormatException e) {
		}
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void function_complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Function<String, String> lambda = mock(Function.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals(Optional.of("value"), collector.function(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test public void function_swallowException() {
		ExceptionCollector collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.function(x -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void function_passException() {
		ExceptionCollector collector = new ExceptionCollector(false);
		try {
			collector.function(x -> {
				throw new NumberFormatException();
			}).apply("input");
			fail();
		} catch (NumberFormatException e) {
		}
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void fromToIntFunction_complete() {
		ExceptionCollector collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToIntFunction<String> lambda = mock(ToIntFunction.class);
		when(lambda.applyAsInt("input")).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromToIntFunction(lambda).apply("input"));
		verify(lambda, only()).applyAsInt("input");
		assertTrue(collector.empty());
	}
	@Test public void fromToIntFunction_swallowException() {
		ExceptionCollector collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromToIntFunction(x -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test public void fromToIntFunction_passException() {
		ExceptionCollector collector = new ExceptionCollector(false);
		try {
			collector.fromToIntFunction(x -> {
				throw new NumberFormatException();
			}).apply("input");
			fail();
		} catch (NumberFormatException e) {
		}
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@RequiredArgsConstructor private static class ExceptionCollector extends ExceptionHandler {
		final boolean swallow;
		final List<Throwable> collected = new ArrayList<>();
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
}
