// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultIntToLongFunctionTest {
	@Test public void full() {
		OptionalIntToLongFunction full = mock(OptionalIntToLongFunction.class);
		when(full.apply(1)).thenReturn(OptionalLong.of(2));
		assertEquals(2, new DefaultIntToLongFunction(full, 3).applyAsLong(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		OptionalIntToLongFunction empty = mock(OptionalIntToLongFunction.class);
		when(empty.apply(1)).thenReturn(OptionalLong.empty());
		assertEquals(3, new DefaultIntToLongFunction(empty, 3).applyAsLong(1));
		verify(empty, only()).apply(1);
	}
}
