// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackBiFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalBiFunction<String, String, String> full = mock(OptionalBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("value", new FallbackBiFunction<String, String, String>(full, fallback).apply("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalBiFunction<String, String, String> empty = mock(OptionalBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("default", new FallbackBiFunction<String, String, String>(empty, fallback).apply("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
		verify(fallback, only()).get();
	}
}
