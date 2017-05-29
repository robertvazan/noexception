// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackLongUnaryOperatorTest {
	@Test public void full() {
		OptionalLongUnaryOperator full = mock(OptionalLongUnaryOperator.class);
		when(full.apply(1L)).thenReturn(OptionalLong.of(2L));
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(2L, new FallbackLongUnaryOperator(full, fallback).applyAsLong(1L));
		verify(full, only()).apply(1L);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalLongUnaryOperator empty = mock(OptionalLongUnaryOperator.class);
		when(empty.apply(1L)).thenReturn(OptionalLong.empty());
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(3L, new FallbackLongUnaryOperator(empty, fallback).applyAsLong(1L));
		verify(empty, only()).apply(1L);
		verify(fallback, only()).getAsLong();
	}
}
