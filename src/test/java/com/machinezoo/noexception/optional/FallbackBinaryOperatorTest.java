// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackBinaryOperatorTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalBinaryOperator<String> full = mock(OptionalBinaryOperator.class);
		when(full.apply("input1", "input2")).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("fallback");
		assertEquals("value", new FallbackBinaryOperator<String>(full, fallback).apply("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalBinaryOperator<String> empty = mock(OptionalBinaryOperator.class);
		when(empty.apply("input1", "input2")).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("fallback");
		assertEquals("fallback", new FallbackBinaryOperator<String>(empty, fallback).apply("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
		verify(fallback, only()).get();
	}
}
