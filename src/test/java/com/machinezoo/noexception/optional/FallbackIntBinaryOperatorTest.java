// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackIntBinaryOperatorTest {
	@Test public void full() {
		OptionalIntBinaryOperator full = mock(OptionalIntBinaryOperator.class);
		when(full.apply(11, 12)).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackIntBinaryOperator(full, fallback).applyAsInt(11, 12));
		verify(full, only()).apply(11, 12);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalIntBinaryOperator empty = mock(OptionalIntBinaryOperator.class);
		when(empty.apply(11, 12)).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackIntBinaryOperator(empty, fallback).applyAsInt(11, 12));
		verify(empty, only()).apply(11, 12);
		verify(fallback, only()).getAsInt();
	}
}
