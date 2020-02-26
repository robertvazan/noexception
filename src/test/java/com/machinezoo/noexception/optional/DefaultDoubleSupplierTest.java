// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultDoubleSupplierTest {
	@Test public void full() {
		OptionalDoubleSupplier full = mock(OptionalDoubleSupplier.class);
		when(full.get()).thenReturn(OptionalDouble.of(2.0));
		assertEquals(2.0, new DefaultDoubleSupplier(full, 3.0).getAsDouble(), 0.1);
		verify(full, only()).get();
	}
	@Test public void empty() {
		OptionalDoubleSupplier empty = mock(OptionalDoubleSupplier.class);
		when(empty.get()).thenReturn(OptionalDouble.empty());
		assertEquals(3.0, new DefaultDoubleSupplier(empty, 3.0).getAsDouble(), 0.1);
		verify(empty, only()).get();
	}
}
