// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultDoubleBinaryOperatorTest {
	@Test
	public void full() {
		OptionalDoubleBinaryOperator full = mock(OptionalDoubleBinaryOperator.class);
		when(full.apply(1.1, 1.2)).thenReturn(OptionalDouble.of(2.0));
		assertEquals(2.0, new DefaultDoubleBinaryOperator(full, 3.0).applyAsDouble(1.1, 1.2), 0.1);
		verify(full, only()).apply(1.1, 1.2);
	}
	@Test
	public void empty() {
		OptionalDoubleBinaryOperator empty = mock(OptionalDoubleBinaryOperator.class);
		when(empty.apply(1.1, 1.2)).thenReturn(OptionalDouble.empty());
		assertEquals(3.0, new DefaultDoubleBinaryOperator(empty, 3.0).applyAsDouble(1.1, 1.2), 0.1);
		verify(empty, only()).apply(1.1, 1.2);
	}
}
