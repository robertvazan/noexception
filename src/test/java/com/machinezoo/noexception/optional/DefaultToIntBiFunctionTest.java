// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultToIntBiFunctionTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalToIntBiFunction<String, String> full = mock(OptionalToIntBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultToIntBiFunction<String, String>(full, 3).applyAsInt("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalToIntBiFunction<String, String> empty = mock(OptionalToIntBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultToIntBiFunction<String, String>(empty, 3).applyAsInt("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
	}
}
