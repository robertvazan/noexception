// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> full = mock(OptionalDoubleFunction.class);
		when(full.apply(1)).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultDoubleFunction<String>(full, "default").apply(1));
		verify(full, only()).apply(1);
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalDoubleFunction<String> empty = mock(OptionalDoubleFunction.class);
		when(empty.apply(1)).thenReturn(Optional.empty());
		assertEquals("default", new DefaultDoubleFunction<String>(empty, "default").apply(1));
		verify(empty, only()).apply(1);
	}
}
