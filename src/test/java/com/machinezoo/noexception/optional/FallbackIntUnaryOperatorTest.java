// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackIntUnaryOperatorTest {
	@Test
	public void full() {
		OptionalIntUnaryOperator full = mock(OptionalIntUnaryOperator.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackIntUnaryOperator(full, fallback).applyAsInt(1));
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test
	public void empty() {
		OptionalIntUnaryOperator empty = mock(OptionalIntUnaryOperator.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackIntUnaryOperator(empty, fallback).applyAsInt(1));
		verify(empty, only()).apply(1);
		verify(fallback, only()).getAsInt();
	}
}
