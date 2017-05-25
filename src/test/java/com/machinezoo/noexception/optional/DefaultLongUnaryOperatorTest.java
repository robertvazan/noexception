// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongUnaryOperatorTest {
	@Test public void full() {
		OptionalLongUnaryOperator full = mock(OptionalLongUnaryOperator.class);
		when(full.apply(1)).thenReturn(OptionalLong.of(2));
		assertEquals(2, new DefaultLongUnaryOperator(full, 3).applyAsLong(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalLongUnaryOperator empty = mock(OptionalLongUnaryOperator.class);
		when(empty.apply(1)).thenReturn(OptionalLong.empty());
		assertEquals(3, new DefaultLongUnaryOperator(empty, 3).applyAsLong(1));
		verify(empty, only()).apply(1);
	}
}
