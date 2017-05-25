// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultSupplierTest {
	@Test public void full() {
		@SuppressWarnings("unchecked") OptionalSupplier<String> full = mock(OptionalSupplier.class);
		when(full.get()).thenReturn(Optional.of("value"));
		assertEquals("value", new DefaultSupplier<String>(full, "default").get());
		verify(full, only()).get();
	}
	@Test public void empty() {
		@SuppressWarnings("unchecked") OptionalSupplier<String> empty = mock(OptionalSupplier.class);
		when(empty.get()).thenReturn(Optional.empty());
		assertEquals("default", new DefaultSupplier<String>(empty, "default").get());
		verify(empty, only()).get();
	}
}
