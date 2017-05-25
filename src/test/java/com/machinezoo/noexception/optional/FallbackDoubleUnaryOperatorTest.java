// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackDoubleUnaryOperatorTest {
	@Test public void full() {
		OptionalDoubleUnaryOperator full = mock(OptionalDoubleUnaryOperator.class);
		when(full.apply(1)).thenReturn(OptionalDouble.of(2.0));
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(2.0, new FallbackDoubleUnaryOperator(full, fallback).applyAsDouble(1), 0.1);
		verify(full, only()).apply(1);
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalDoubleUnaryOperator empty = mock(OptionalDoubleUnaryOperator.class);
		when(empty.apply(1)).thenReturn(OptionalDouble.empty());
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(3.0, new FallbackDoubleUnaryOperator(empty, fallback).applyAsDouble(1), 0.1);
		verify(empty, only()).apply(1);
		verify(fallback, only()).getAsDouble();
	}
}
