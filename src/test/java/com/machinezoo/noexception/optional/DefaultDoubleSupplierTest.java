// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.*;

public class DefaultDoubleSupplierTest {
	@Test public void full() {
		OptionalDoubleSupplier full = mock(OptionalDoubleSupplier.class);
		when(full.get()).thenReturn(OptionalDouble.of(2));
		assertEquals(2, new DefaultDoubleSupplier(full, 3).getAsDouble(), 0.1);
		verify(full, only()).get();
	}
	@Test public void empty() {
		OptionalDoubleSupplier empty = mock(OptionalDoubleSupplier.class);
		when(empty.get()).thenReturn(OptionalDouble.empty());
		assertEquals(3, new DefaultDoubleSupplier(empty, 3).getAsDouble(), 0.1);
		verify(empty, only()).get();
	}
}
