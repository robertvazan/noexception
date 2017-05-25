// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultUnaryOperatorTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalUnaryOperator<String> full = mock(OptionalUnaryOperator.class);
		when(full.apply("input")).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultUnaryOperator<String>(full, "default").apply("input"));
		verify(full, only()).apply("input");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalUnaryOperator<String> empty = mock(OptionalUnaryOperator.class);
		when(empty.apply("input")).thenReturn(Optional.empty());
		assertEquals("default", new DefaultUnaryOperator<String>(empty, "default").apply("input"));
		verify(empty, only()).apply("input");
	}
}
