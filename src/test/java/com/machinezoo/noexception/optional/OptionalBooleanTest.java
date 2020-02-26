// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;

public class OptionalBooleanTest {
	@Test public void empty() {
		assertSame(OptionalBoolean.empty(), OptionalBoolean.empty());
		assertFalse(OptionalBoolean.empty().isPresent());
	}
	@Test public void of() {
		assertSame(OptionalBoolean.of(false), OptionalBoolean.of(false));
		assertSame(OptionalBoolean.of(true), OptionalBoolean.of(true));
		assertTrue(OptionalBoolean.of(false).isPresent());
		assertTrue(OptionalBoolean.of(true).isPresent());
		assertFalse(OptionalBoolean.of(false).getAsBoolean());
		assertTrue(OptionalBoolean.of(true).getAsBoolean());
	}
	@Test public void isPresent() {
		assertFalse(OptionalBoolean.empty().isPresent());
		assertTrue(OptionalBoolean.of(false).isPresent());
		assertTrue(OptionalBoolean.of(true).isPresent());
	}
	@Test public void getAsBoolean() {
		assertFalse(OptionalBoolean.of(false).getAsBoolean());
		assertTrue(OptionalBoolean.of(true).getAsBoolean());
		assertThrows(NoSuchElementException.class, () -> OptionalBoolean.empty().getAsBoolean());
	}
	@Test public void orElse() {
		assertFalse(OptionalBoolean.empty().orElse(false));
		assertTrue(OptionalBoolean.empty().orElse(true));
		assertFalse(OptionalBoolean.of(false).orElse(true));
		assertTrue(OptionalBoolean.of(true).orElse(false));
	}
	@Test public void orElseGet() {
		assertFalse(OptionalBoolean.empty().orElseGet(() -> false));
		assertTrue(OptionalBoolean.empty().orElseGet(() -> true));
		assertFalse(OptionalBoolean.of(false).orElseGet(() -> {
			fail();
			return true;
		}));
		assertTrue(OptionalBoolean.of(true).orElseGet(() -> {
			fail();
			return false;
		}));
	}
	public static class SampleException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
	@Test public void orElseThrow_of() {
		assertFalse(OptionalBoolean.of(false).orElseThrow(SampleException::new));
		assertTrue(OptionalBoolean.of(true).orElseThrow(SampleException::new));
		assertThrows(SampleException.class, () -> OptionalBoolean.empty().orElseThrow(SampleException::new));
	}
	@Test public void ifPresent() {
		OptionalBoolean.empty().ifPresent(n -> fail());
		IntConsumer consumeFalse = mock(IntConsumer.class);
		OptionalBoolean.of(false).ifPresent(consumeFalse);
		verify(consumeFalse, only()).accept(0);
		IntConsumer consumeTrue = mock(IntConsumer.class);
		OptionalBoolean.of(true).ifPresent(consumeTrue);
		verify(consumeTrue, only()).accept(1);
	}
	@Test public void equals() {
		assertEquals(OptionalBoolean.empty(), OptionalBoolean.empty());
		assertEquals(OptionalBoolean.of(false), OptionalBoolean.of(false));
		assertEquals(OptionalBoolean.of(true), OptionalBoolean.of(true));
		assertNotEquals(OptionalBoolean.empty(), OptionalBoolean.of(false));
		assertNotEquals(OptionalBoolean.empty(), OptionalBoolean.of(true));
		assertNotEquals(OptionalBoolean.of(false), OptionalBoolean.of(true));
	}
	@Test public void hashCodeValue() {
		assertEquals(OptionalBoolean.empty().hashCode(), OptionalBoolean.empty().hashCode());
		assertEquals(OptionalBoolean.of(false).hashCode(), OptionalBoolean.of(false).hashCode());
		assertEquals(OptionalBoolean.of(true).hashCode(), OptionalBoolean.of(true).hashCode());
		Stream<OptionalBoolean> all = Stream.of(OptionalBoolean.empty(), OptionalBoolean.of(false), OptionalBoolean.of(true));
		assertEquals(3, all.map(OptionalBoolean::hashCode).collect(toSet()).size());
	}
	@Test public void toStringTest() {
		assertEquals("empty", OptionalBoolean.empty().toString());
		assertEquals("false", OptionalBoolean.of(false).toString());
		assertEquals("true", OptionalBoolean.of(true).toString());
	}
}
