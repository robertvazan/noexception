// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultDoubleFunctionTest {
	@Test
	public void full() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> full = mock(OptionalDoubleFunction.class);
		when(full.apply(1.0)).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultDoubleFunction<String>(full, "default").apply(1.0));
		verify(full, only()).apply(1.0);
	}
	@Test
	public void empty() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> empty = mock(OptionalDoubleFunction.class);
		when(empty.apply(1.0)).thenReturn(Optional.empty());
		assertEquals("default", new DefaultDoubleFunction<String>(empty, "default").apply(1.0));
		verify(empty, only()).apply(1.0);
	}
}
