// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackComparatorTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalComparator<String> full = mock(OptionalComparator.class);
		when(full.compare("input1", "input2")).thenReturn(OptionalInt.of(2));
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(2, new FallbackComparator<String>(full, fallback).compare("input1", "input2"));
		verify(full, only()).compare("input1", "input2");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalComparator<String> empty = mock(OptionalComparator.class);
		when(empty.compare("input1", "input2")).thenReturn(OptionalInt.empty());
		IntSupplier fallback = mock(IntSupplier.class);
		when(fallback.getAsInt()).thenReturn(3);
		assertEquals(3, new FallbackComparator<String>(empty, fallback).compare("input1", "input2"));
		verify(empty, only()).compare("input1", "input2");
		verify(fallback, only()).getAsInt();
	}
}
