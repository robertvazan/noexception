// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackIntPredicateTest {
	@Test public void full() {
		OptionalIntPredicate full = mock(OptionalIntPredicate.class);
		when(full.test(1)).thenReturn(OptionalBoolean.of(true));
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(true, new FallbackIntPredicate(full, fallback).test(1));
		verify(full, only()).test(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalIntPredicate empty = mock(OptionalIntPredicate.class);
		when(empty.test(1)).thenReturn(OptionalBoolean.empty());
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(false, new FallbackIntPredicate(empty, fallback).test(1));
		verify(empty, only()).test(1);
		verify(fallback, only()).getAsBoolean();
	}
}
