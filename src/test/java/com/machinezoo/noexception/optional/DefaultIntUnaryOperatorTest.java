// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultIntUnaryOperatorTest {
	@Test public void full() {
		OptionalIntUnaryOperator full = mock(OptionalIntUnaryOperator.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultIntUnaryOperator(full, 3).applyAsInt(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalIntUnaryOperator empty = mock(OptionalIntUnaryOperator.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultIntUnaryOperator(empty, 3).applyAsInt(1));
		verify(empty, only()).apply(1);
	}
}
