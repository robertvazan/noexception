// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleBinaryOperatorTest {
	@Test public void full() {
		OptionalDoubleBinaryOperator full = mock(OptionalDoubleBinaryOperator.class);
		when(full.apply(11, 12)).thenReturn(OptionalDouble.of(2));
		assertEquals(2, new DefaultDoubleBinaryOperator(full, 3).applyAsDouble(11, 12), 0.1);
		verify(full, only()).apply(11, 12);
	}
	@Test public void empty() {
		OptionalDoubleBinaryOperator empty = mock(OptionalDoubleBinaryOperator.class);
		when(empty.apply(11, 12)).thenReturn(OptionalDouble.empty());
		assertEquals(3, new DefaultDoubleBinaryOperator(empty, 3).applyAsDouble(11, 12), 0.1);
		verify(empty, only()).apply(11, 12);
	}
}
