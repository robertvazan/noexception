// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultToIntFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToIntFunction<String> full = mock(OptionalToIntFunction.class);
		when(full.apply("input")).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultToIntFunction<String>(full, 3).applyAsInt("input"));
		verify(full, only()).apply("input");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToIntFunction<String> empty = mock(OptionalToIntFunction.class);
		when(empty.apply("input")).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultToIntFunction<String>(empty, 3).applyAsInt("input"));
		verify(empty, only()).apply("input");
	}
}
