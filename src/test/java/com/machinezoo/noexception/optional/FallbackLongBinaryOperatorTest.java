// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackLongBinaryOperatorTest {
	@Test public void full() {
		OptionalLongBinaryOperator full = mock(OptionalLongBinaryOperator.class);
		when(full.apply(11, 12)).thenReturn(OptionalLong.of(2L));
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(2L, new FallbackLongBinaryOperator(full, fallback).applyAsLong(11, 12));
		verify(full, only()).apply(11, 12);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalLongBinaryOperator empty = mock(OptionalLongBinaryOperator.class);
		when(empty.apply(11, 12)).thenReturn(OptionalLong.empty());
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(3L, new FallbackLongBinaryOperator(empty, fallback).applyAsLong(11, 12));
		verify(empty, only()).apply(11, 12);
		verify(fallback, only()).getAsLong();
	}
}
