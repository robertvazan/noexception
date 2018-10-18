// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultBiFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalBiFunction<String, String, String> full = mock(OptionalBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultBiFunction<String, String, String>(full, "default").apply("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalBiFunction<String, String, String> empty = mock(OptionalBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(Optional.empty());
		assertEquals("default", new DefaultBiFunction<String, String, String>(empty, "default").apply("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
	}
}
