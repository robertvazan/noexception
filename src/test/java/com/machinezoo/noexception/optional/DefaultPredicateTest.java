// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;

public class DefaultPredicateTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalPredicate<String> full = mock(OptionalPredicate.class);
		when(full.test("input")).thenReturn(OptionalBoolean.of(true));
		assertEquals(true, new DefaultPredicate<String>(full, false).test("input"));
		verify(full, only()).test("input");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalPredicate<String> empty = mock(OptionalPredicate.class);
		when(empty.test("input")).thenReturn(OptionalBoolean.empty());
		assertEquals(false, new DefaultPredicate<String>(empty, false).test("input"));
		verify(empty, only()).test("input");
	}
}
