// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.*;

public class FallbackPredicateTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalPredicate<String> full = mock(OptionalPredicate.class);
		when(full.test("input")).thenReturn(OptionalBoolean.of(true));
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(true, new FallbackPredicate<String>(full, fallback).test("input"));
		verify(full, only()).test("input");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalPredicate<String> empty = mock(OptionalPredicate.class);
		when(empty.test("input")).thenReturn(OptionalBoolean.empty());
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(false, new FallbackPredicate<String>(empty, fallback).test("input"));
		verify(empty, only()).test("input");
		verify(fallback, only()).getAsBoolean();
	}
}
