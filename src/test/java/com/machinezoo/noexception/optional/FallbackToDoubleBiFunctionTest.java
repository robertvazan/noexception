// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.*;

public class FallbackToDoubleBiFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToDoubleBiFunction<String, String> full = mock(OptionalToDoubleBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(OptionalDouble.of(2.0));
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(2.0, new FallbackToDoubleBiFunction<String, String>(full, fallback).applyAsDouble("input1", "input2"), 0.1);
		verify(full, only()).apply("input1", "input2");
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToDoubleBiFunction<String, String> empty = mock(OptionalToDoubleBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(OptionalDouble.empty());
		DoubleSupplier fallback = mock(DoubleSupplier.class);
		when(fallback.getAsDouble()).thenReturn(3.0);
		assertEquals(3.0, new FallbackToDoubleBiFunction<String, String>(empty, fallback).applyAsDouble("input1", "input2"), 0.1);
		verify(empty, only()).apply("input1", "input2");
		verify(fallback, only()).getAsDouble();
	}
}
