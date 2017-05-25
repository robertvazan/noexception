// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongToDoubleFunctionTest {
	@Test public void full() {
		OptionalLongToDoubleFunction full = mock(OptionalLongToDoubleFunction.class);
		when(full.apply(1)).thenReturn(OptionalDouble.of(2));
		assertEquals(2, new DefaultLongToDoubleFunction(full, 3).applyAsDouble(1), 0.1);
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalLongToDoubleFunction empty = mock(OptionalLongToDoubleFunction.class);
		when(empty.apply(1)).thenReturn(OptionalDouble.empty());
		assertEquals(3, new DefaultLongToDoubleFunction(empty, 3).applyAsDouble(1), 0.1);
		verify(empty, only()).apply(1);
	}
}
