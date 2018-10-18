// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultLongSupplierTest {
	@Test public void full() {
		OptionalLongSupplier full = mock(OptionalLongSupplier.class);
		when(full.get()).thenReturn(OptionalLong.of(2L));
		assertEquals(2L, new DefaultLongSupplier(full, 3L).getAsLong());
		verify(full, only()).get();
	}
	@Test public void empty() {
		OptionalLongSupplier empty = mock(OptionalLongSupplier.class);
		when(empty.get()).thenReturn(OptionalLong.empty());
		assertEquals(3L, new DefaultLongSupplier(empty, 3L).getAsLong());
		verify(empty, only()).get();
	}
}
