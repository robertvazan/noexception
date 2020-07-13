// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class DefaultBooleanSupplierTest {
	@Test
	public void full() {
		OptionalBooleanSupplier full = mock(OptionalBooleanSupplier.class);
		when(full.get()).thenReturn(OptionalBoolean.of(true));
		assertEquals(true, new DefaultBooleanSupplier(full, false).getAsBoolean());
		verify(full, only()).get();
	}
	@Test
	public void empty() {
		OptionalBooleanSupplier empty = mock(OptionalBooleanSupplier.class);
		when(empty.get()).thenReturn(OptionalBoolean.empty());
		assertEquals(false, new DefaultBooleanSupplier(empty, false).getAsBoolean());
		verify(empty, only()).get();
	}
}
