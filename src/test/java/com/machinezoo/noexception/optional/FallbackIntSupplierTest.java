// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackIntSupplierTest {
	@Test public void full() {
		OptionalIntSupplier full = mock(OptionalIntSupplier.class);
		when(full.get()).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackIntSupplier(full, fallback).getAsInt());
		verify(full, only()).get();
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalIntSupplier empty = mock(OptionalIntSupplier.class);
		when(empty.get()).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackIntSupplier(empty, fallback).getAsInt());
		verify(empty, only()).get();
		verify(fallback, only()).getAsInt();
	}
}
