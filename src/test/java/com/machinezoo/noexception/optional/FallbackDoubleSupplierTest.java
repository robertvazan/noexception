// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackDoubleSupplierTest {
	@Test
	public void full() {
		OptionalDoubleSupplier full = mock(OptionalDoubleSupplier.class);
		when(full.get()).thenReturn(OptionalDouble.of(2.0));
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(2.0, new FallbackDoubleSupplier(full, fallback).getAsDouble(), 0.1);
		verify(full, only()).get();
		verifyNoMoreInteractions(fallback);
	}
	@Test
	public void empty() {
		OptionalDoubleSupplier empty = mock(OptionalDoubleSupplier.class);
		when(empty.get()).thenReturn(OptionalDouble.empty());
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(3.0, new FallbackDoubleSupplier(empty, fallback).getAsDouble(), 0.1);
		verify(empty, only()).get();
		verify(fallback, only()).getAsDouble();
	}
}
