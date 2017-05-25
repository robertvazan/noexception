// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.*;

public class FallbackBooleanSupplierTest {
	@Test public void full() {
		OptionalBooleanSupplier full = mock(OptionalBooleanSupplier.class);
		when(full.get()).thenReturn(OptionalBoolean.of(true));
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(true, new FallbackBooleanSupplier(full, fallback).getAsBoolean());
		verify(full, only()).get();
		verifyNoMoreInteractions(fallback);
	}
	@Test public void empty() {
		OptionalBooleanSupplier empty = mock(OptionalBooleanSupplier.class);
		when(empty.get()).thenReturn(OptionalBoolean.empty());
		BooleanSupplier fallback = mock(BooleanSupplier.class);
		when(fallback.getAsBoolean()).thenReturn(false);
		assertEquals(false, new FallbackBooleanSupplier(empty, fallback).getAsBoolean());
		verify(empty, only()).get();
		verify(fallback, only()).getAsBoolean();
	}
}
