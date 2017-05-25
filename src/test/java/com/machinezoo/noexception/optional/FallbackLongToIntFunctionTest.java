// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackLongToIntFunctionTest {
	@Test public void full() {
		OptionalLongToIntFunction full = mock(OptionalLongToIntFunction.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackLongToIntFunction(full, fallback).applyAsInt(1));
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalLongToIntFunction empty = mock(OptionalLongToIntFunction.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackLongToIntFunction(empty, fallback).applyAsInt(1));
		verify(empty, only()).apply(1);
		verify(fallback, only()).getAsInt();
	}
}
