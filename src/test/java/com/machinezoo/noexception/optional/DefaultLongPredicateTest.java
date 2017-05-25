// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;

public class DefaultLongPredicateTest {
	@Test public void full() {
		OptionalLongPredicate full = mock(OptionalLongPredicate.class);
		when(full.test(1)).thenReturn(OptionalBoolean.of(true));
		assertEquals(true, new DefaultLongPredicate(full, false).test(1));
		verify(full, only()).test(1);
	}
	@Test public void empty() {
		OptionalLongPredicate empty = mock(OptionalLongPredicate.class);
		when(empty.test(1)).thenReturn(OptionalBoolean.empty());
		assertEquals(false, new DefaultLongPredicate(empty, false).test(1));
		verify(empty, only()).test(1);
	}
}
