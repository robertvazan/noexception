// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackDoublePredicateTest {
	@Test public void full() {
		OptionalDoublePredicate full = mock(OptionalDoublePredicate.class);
		when(full.test(1.0)).thenReturn(OptionalBoolean.of(true));
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(true, new FallbackDoublePredicate(full, fallback).test(1.0));
		verify(full, only()).test(1.0);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalDoublePredicate empty = mock(OptionalDoublePredicate.class);
		when(empty.test(1.0)).thenReturn(OptionalBoolean.empty());
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(false, new FallbackDoublePredicate(empty, fallback).test(1.0));
		verify(empty, only()).test(1.0);
		verify(fallback, only()).getAsBoolean();
	}
}
