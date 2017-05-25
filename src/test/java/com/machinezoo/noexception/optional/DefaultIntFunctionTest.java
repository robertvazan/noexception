// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultIntFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalIntFunction<String> full = mock(OptionalIntFunction.class);
		when(full.apply(1)).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultIntFunction<String>(full, "default").apply(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalIntFunction<String> empty = mock(OptionalIntFunction.class);
		when(empty.apply(1)).thenReturn(Optional.empty());
		assertEquals("default", new DefaultIntFunction<String>(empty, "default").apply(1));
		verify(empty, only()).apply(1);
	}
}
