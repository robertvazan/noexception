// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultToLongBiFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToLongBiFunction<String, String> full = mock(OptionalToLongBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(OptionalLong.of(2L));
		assertEquals(2L, new DefaultToLongBiFunction<String, String>(full, 3L).applyAsLong("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToLongBiFunction<String, String> empty = mock(OptionalToLongBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(OptionalLong.empty());
		assertEquals(3L, new DefaultToLongBiFunction<String, String>(empty, 3L).applyAsLong("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
	}
}
