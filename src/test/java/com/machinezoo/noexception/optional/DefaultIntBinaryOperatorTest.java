// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultIntBinaryOperatorTest {
	@Test public void full() {
		OptionalIntBinaryOperator full = mock(OptionalIntBinaryOperator.class);
		when(full.apply(11, 12)).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultIntBinaryOperator(full, 3).applyAsInt(11, 12));
		verify(full, only()).apply(11, 12);
	}
	@Test public void empty() {
		OptionalIntBinaryOperator empty = mock(OptionalIntBinaryOperator.class);
		when(empty.apply(11, 12)).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultIntBinaryOperator(empty, 3).applyAsInt(11, 12));
		verify(empty, only()).apply(11, 12);
	}
}
