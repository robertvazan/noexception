// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultToLongFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToLongFunction<String> full = mock(OptionalToLongFunction.class);
		when(full.apply("input")).thenReturn(OptionalLong.of(2));
		assertEquals(2, new DefaultToLongFunction<String>(full, 3).applyAsLong("input"));
		verify(full, only()).apply("input");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToLongFunction<String> empty = mock(OptionalToLongFunction.class);
		when(empty.apply("input")).thenReturn(OptionalLong.empty());
		assertEquals(3, new DefaultToLongFunction<String>(empty, 3).applyAsLong("input"));
		verify(empty, only()).apply("input");
	}
}
