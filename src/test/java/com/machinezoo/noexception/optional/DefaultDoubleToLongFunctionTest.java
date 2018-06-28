// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleToLongFunctionTest {
	@Test public void full() {
		OptionalDoubleToLongFunction full = mock(OptionalDoubleToLongFunction.class);
		when(full.apply(1.0)).thenReturn(OptionalLong.of(2L));
		assertEquals(2L, new DefaultDoubleToLongFunction(full, 3L).applyAsLong(1.0));
		verify(full, only()).apply(1.0);
	}
	@Test public void empty() {
		OptionalDoubleToLongFunction empty = mock(OptionalDoubleToLongFunction.class);
		when(empty.apply(1.0)).thenReturn(OptionalLong.empty());
		assertEquals(3L, new DefaultDoubleToLongFunction(empty, 3L).applyAsLong(1.0));
		verify(empty, only()).apply(1.0);
	}
}
