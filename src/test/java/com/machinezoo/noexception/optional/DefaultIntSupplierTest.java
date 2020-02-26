// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultIntSupplierTest {
	@Test public void full() {
		OptionalIntSupplier full = mock(OptionalIntSupplier.class);
		when(full.get()).thenReturn(OptionalInt.of(2));
		assertEquals(2, new DefaultIntSupplier(full, 3).getAsInt());
		verify(full, only()).get();
	}
	@Test public void empty() {
		OptionalIntSupplier empty = mock(OptionalIntSupplier.class);
		when(empty.get()).thenReturn(OptionalInt.empty());
		assertEquals(3, new DefaultIntSupplier(empty, 3).getAsInt());
		verify(empty, only()).get();
	}
}
