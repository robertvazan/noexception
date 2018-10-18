// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackDoubleToLongFunctionTest {
	@Test public void full() {
		OptionalDoubleToLongFunction full = mock(OptionalDoubleToLongFunction.class);
		when(full.apply(1.0)).thenReturn(OptionalLong.of(2L));
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(2L, new FallbackDoubleToLongFunction(full, fallback).applyAsLong(1.0));
		verify(full, only()).apply(1.0);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalDoubleToLongFunction empty = mock(OptionalDoubleToLongFunction.class);
		when(empty.apply(1.0)).thenReturn(OptionalLong.empty());
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(3L, new FallbackDoubleToLongFunction(empty, fallback).applyAsLong(1.0));
		verify(empty, only()).apply(1.0);
		verify(fallback, only()).getAsLong();
	}
}
