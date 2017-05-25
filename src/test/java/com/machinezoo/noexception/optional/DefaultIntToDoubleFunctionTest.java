// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultIntToDoubleFunctionTest {
	@Test public void full() {
		OptionalIntToDoubleFunction full = mock(OptionalIntToDoubleFunction.class);
		when(full.apply(1)).thenReturn(OptionalDouble.of(2.0));
		assertEquals(2.0, new DefaultIntToDoubleFunction(full, 3.0).applyAsDouble(1), 0.1);
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalIntToDoubleFunction empty = mock(OptionalIntToDoubleFunction.class);
		when(empty.apply(1)).thenReturn(OptionalDouble.empty());
		assertEquals(3.0, new DefaultIntToDoubleFunction(empty, 3.0).applyAsDouble(1), 0.1);
		verify(empty, only()).apply(1);
	}
}
