// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackSupplierTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalSupplier<String> full = mock(OptionalSupplier.class);
		when(full.get()).thenReturn(Optional.of("value"));
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("value", new FallbackSupplier<String>(full, fallback).get());
		verify(full, only()).get();
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalSupplier<String> empty = mock(OptionalSupplier.class);
		when(empty.get()).thenReturn(Optional.empty());
		@SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
		when(fallback.get()).thenReturn("default");
		assertEquals("default", new FallbackSupplier<String>(empty, fallback).get());
		verify(empty, only()).get();
		verify(fallback, only()).get();
	}
}
