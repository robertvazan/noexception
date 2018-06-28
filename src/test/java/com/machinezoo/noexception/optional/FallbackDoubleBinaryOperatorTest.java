// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackDoubleBinaryOperatorTest {
	@Test public void full() {
		OptionalDoubleBinaryOperator full = mock(OptionalDoubleBinaryOperator.class);
		when(full.apply(1.1, 1.2)).thenReturn(OptionalDouble.of(2.0));
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(2.0, new FallbackDoubleBinaryOperator(full, fallback).applyAsDouble(1.1, 1.2), 0.1);
		verify(full, only()).apply(1.1, 1.2);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalDoubleBinaryOperator empty = mock(OptionalDoubleBinaryOperator.class);
		when(empty.apply(1.1, 1.2)).thenReturn(OptionalDouble.empty());
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(3.0, new FallbackDoubleBinaryOperator(empty, fallback).applyAsDouble(1.1, 1.2), 0.1);
		verify(empty, only()).apply(1.1, 1.2);
		verify(fallback, only()).getAsDouble();
	}
}
