// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleToIntFunctionTest {
	@Test public void full() {
		OptionalDoubleToIntFunction full = mock(OptionalDoubleToIntFunction.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultDoubleToIntFunction(full, 3).applyAsInt(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalDoubleToIntFunction empty = mock(OptionalDoubleToIntFunction.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultDoubleToIntFunction(empty, 3).applyAsInt(1));
		verify(empty, only()).apply(1);
	}
}
