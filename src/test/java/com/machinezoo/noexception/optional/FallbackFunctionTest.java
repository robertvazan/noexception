// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalFunction<String, String> full = mock(OptionalFunction.class);
		when(full.apply("input")).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("value", new FallbackFunction<String, String>(full, fallback).apply("input"));
		verify(full, only()).apply("input");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalFunction<String, String> empty = mock(OptionalFunction.class);
		when(empty.apply("input")).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("default", new FallbackFunction<String, String>(empty, fallback).apply("input"));
		verify(empty, only()).apply("input");
		verify(fallback, only()).get();
	}
}
