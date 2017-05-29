// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackIntFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalIntFunction<String> full = mock(OptionalIntFunction.class);
		when(full.apply(1)).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("value", new FallbackIntFunction<String>(full, fallback).apply(1));
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalIntFunction<String> empty = mock(OptionalIntFunction.class);
		when(empty.apply(1)).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("default", new FallbackIntFunction<String>(empty, fallback).apply(1));
		verify(empty, only()).apply(1);
		verify(fallback, only()).get();
	}
}
