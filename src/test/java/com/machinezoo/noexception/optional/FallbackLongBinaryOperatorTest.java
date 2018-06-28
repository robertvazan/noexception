// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackLongBinaryOperatorTest {
	@Test public void full() {
		OptionalLongBinaryOperator full = mock(OptionalLongBinaryOperator.class);
		when(full.apply(11L, 12L)).thenReturn(OptionalLong.of(2L));
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(2L, new FallbackLongBinaryOperator(full, fallback).applyAsLong(11L, 12L));
		verify(full, only()).apply(11L, 12L);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalLongBinaryOperator empty = mock(OptionalLongBinaryOperator.class);
		when(empty.apply(11L, 12L)).thenReturn(OptionalLong.empty());
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(3L, new FallbackLongBinaryOperator(empty, fallback).applyAsLong(11L, 12L));
		verify(empty, only()).apply(11L, 12L);
		verify(fallback, only()).getAsLong();
	}
}
