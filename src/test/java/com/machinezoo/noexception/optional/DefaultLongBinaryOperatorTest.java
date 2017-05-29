// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongBinaryOperatorTest {
	@Test public void full() {
		OptionalLongBinaryOperator full = mock(OptionalLongBinaryOperator.class);
		when(full.apply(11L, 12L)).thenReturn(OptionalLong.of(2L));
		assertEquals(2L, new DefaultLongBinaryOperator(full, 3L).applyAsLong(11L, 12L));
		verify(full, only()).apply(11L, 12L);
	}
	@Test public void empty() {
		OptionalLongBinaryOperator empty = mock(OptionalLongBinaryOperator.class);
		when(empty.apply(11L, 12L)).thenReturn(OptionalLong.empty());
		assertEquals(3L, new DefaultLongBinaryOperator(empty, 3L).applyAsLong(11L, 12L));
		verify(empty, only()).apply(11L, 12L);
	}
}
