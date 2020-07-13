// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultComparatorTest {
	@Test
	public void full() {
		@SuppressWarnings("unchecked") OptionalComparator<String> full = mock(OptionalComparator.class);
		when(full.compare("input1", "input2")).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultComparator<String>(full, 3).compare("input1", "input2"));
		verify(full, only()).compare("input1", "input2");
	}
	@Test
	public void empty() {
		@SuppressWarnings("unchecked") OptionalComparator<String> empty = mock(OptionalComparator.class);
		when(empty.compare("input1", "input2")).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultComparator<String>(empty, 3).compare("input1", "input2"));
		verify(empty, only()).compare("input1", "input2");
	}
}
