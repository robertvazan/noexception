// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class DefaultLongPredicateTest {
	@Test
	public void full() {
		OptionalLongPredicate full = mock(OptionalLongPredicate.class);
		when(full.test(1L)).thenReturn(OptionalBoolean.of(true));
		assertEquals(true, new DefaultLongPredicate(full, false).test(1L));
		verify(full, only()).test(1L);
	}
	@Test
	public void empty() {
		OptionalLongPredicate empty = mock(OptionalLongPredicate.class);
		when(empty.test(1L)).thenReturn(OptionalBoolean.empty());
		assertEquals(false, new DefaultLongPredicate(empty, false).test(1L));
		verify(empty, only()).test(1L);
	}
}
