// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackLongFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalLongFunction<String> full = mock(OptionalLongFunction.class);
		when(full.apply(1)).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("fallback");
		assertEquals("value", new FallbackLongFunction<String>(full, fallback).apply(1));
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalLongFunction<String> empty = mock(OptionalLongFunction.class);
		when(empty.apply(1)).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("fallback");
		assertEquals("fallback", new FallbackLongFunction<String>(empty, fallback).apply(1));
		verify(empty, only()).apply(1);
		verify(fallback, only()).get();
	}
}
