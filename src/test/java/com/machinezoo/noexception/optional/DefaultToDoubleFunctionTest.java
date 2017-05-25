// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultToDoubleFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToDoubleFunction<String> full = mock(OptionalToDoubleFunction.class);
		when(full.apply("input")).thenReturn(OptionalDouble.of(2));
		assertEquals(2, new DefaultToDoubleFunction<String>(full, 3).applyAsDouble("input"), 0.1);
		verify(full, only()).apply("input");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToDoubleFunction<String> empty = mock(OptionalToDoubleFunction.class);
		when(empty.apply("input")).thenReturn(OptionalDouble.empty());
		assertEquals(3, new DefaultToDoubleFunction<String>(empty, 3).applyAsDouble("input"), 0.1);
		verify(empty, only()).apply("input");
	}
}
