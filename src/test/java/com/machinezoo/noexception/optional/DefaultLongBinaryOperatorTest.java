// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongBinaryOperatorTest {
	@Test public void full() {
		OptionalLongBinaryOperator full = mock(OptionalLongBinaryOperator.class);
		when(full.apply(11, 12)).thenReturn(OptionalLong.of(2));
		assertEquals(2, new DefaultLongBinaryOperator(full, 3).applyAsLong(11, 12));
		verify(full, only()).apply(11, 12);
	}
	@Test public void empty() {
		OptionalLongBinaryOperator empty = mock(OptionalLongBinaryOperator.class);
		when(empty.apply(11, 12)).thenReturn(OptionalLong.empty());
		assertEquals(3, new DefaultLongBinaryOperator(empty, 3).applyAsLong(11, 12));
		verify(empty, only()).apply(11, 12);
	}
}
