// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackDoubleFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> full = mock(OptionalDoubleFunction.class);
		when(full.apply(1.0)).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("value", new FallbackDoubleFunction<String>(full, fallback).apply(1.0));
		verify(full, only()).apply(1.0);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> empty = mock(OptionalDoubleFunction.class);
		when(empty.apply(1.0)).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("default", new FallbackDoubleFunction<String>(empty, fallback).apply(1.0));
		verify(empty, only()).apply(1.0);
		verify(fallback, only()).get();
	}
}
