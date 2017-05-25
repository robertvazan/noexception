// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackDoubleToIntFunctionTest {
	@Test public void full() {
		OptionalDoubleToIntFunction full = mock(OptionalDoubleToIntFunction.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackDoubleToIntFunction(full, fallback).applyAsInt(1));
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalDoubleToIntFunction empty = mock(OptionalDoubleToIntFunction.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackDoubleToIntFunction(empty, fallback).applyAsInt(1));
		verify(empty, only()).apply(1);
		verify(fallback, only()).getAsInt();
	}
}
