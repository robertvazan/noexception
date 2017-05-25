// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleUnaryOperatorTest {
	@Test public void full() {
		OptionalDoubleUnaryOperator full = mock(OptionalDoubleUnaryOperator.class);
		when(full.apply(1)).thenReturn(OptionalDouble.of(2.0));
		assertEquals(2.0, new DefaultDoubleUnaryOperator(full, 3.0).applyAsDouble(1), 0.1);
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalDoubleUnaryOperator empty = mock(OptionalDoubleUnaryOperator.class);
		when(empty.apply(1)).thenReturn(OptionalDouble.empty());
		assertEquals(3.0, new DefaultDoubleUnaryOperator(empty, 3.0).applyAsDouble(1), 0.1);
		verify(empty, only()).apply(1);
	}
}
