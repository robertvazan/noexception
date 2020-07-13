// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackToLongBiFunctionTest {
	@Test
	public void full() {
		@SuppressWarnings("unchecked") OptionalToLongBiFunction<String, String> full = mock(OptionalToLongBiFunction.class);
		when(full.apply("input1", "input2")).thenReturn(OptionalLong.of(2L));
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(2L, new FallbackToLongBiFunction<String, String>(full, fallback).applyAsLong("input1", "input2"));
		verify(full, only()).apply("input1", "input2");
		verifyNoMoreInteractions(fallback);
	}
	@Test
	public void empty() {
		@SuppressWarnings("unchecked") OptionalToLongBiFunction<String, String> empty = mock(OptionalToLongBiFunction.class);
		when(empty.apply("input1", "input2")).thenReturn(OptionalLong.empty());
		LongSupplier fallback = mock(LongSupplier.class);
		when(fallback.getAsLong()).thenReturn(3L);
		assertEquals(3L, new FallbackToLongBiFunction<String, String>(empty, fallback).applyAsLong("input1", "input2"));
		verify(empty, only()).apply("input1", "input2");
		verify(fallback, only()).getAsLong();
	}
}
