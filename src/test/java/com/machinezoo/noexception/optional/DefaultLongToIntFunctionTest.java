// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongToIntFunctionTest {
	@Test public void full() {
		OptionalLongToIntFunction full = mock(OptionalLongToIntFunction.class);
		when(full.apply(1)).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultLongToIntFunction(full, 3).applyAsInt(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalLongToIntFunction empty = mock(OptionalLongToIntFunction.class);
		when(empty.apply(1)).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultLongToIntFunction(empty, 3).applyAsInt(1));
		verify(empty, only()).apply(1);
	}
}
