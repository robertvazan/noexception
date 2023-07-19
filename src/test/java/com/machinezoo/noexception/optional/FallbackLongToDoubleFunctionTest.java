// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackLongToDoubleFunctionTest {
    @Test
    public void full() {
        OptionalLongToDoubleFunction full = mock(OptionalLongToDoubleFunction.class);
        when(full.apply(1L)).thenReturn(OptionalDouble.of(2.0));
        DoubleSupplier fallback = mock(DoubleSupplier.class);
        when(fallback.getAsDouble()).thenReturn(3.0);
        assertEquals(2.0, new FallbackLongToDoubleFunction(full, fallback).applyAsDouble(1L), 0.1);
        verify(full, only()).apply(1L);
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        OptionalLongToDoubleFunction empty = mock(OptionalLongToDoubleFunction.class);
        when(empty.apply(1L)).thenReturn(OptionalDouble.empty());
        DoubleSupplier fallback = mock(DoubleSupplier.class);
        when(fallback.getAsDouble()).thenReturn(3.0);
        assertEquals(3.0, new FallbackLongToDoubleFunction(empty, fallback).applyAsDouble(1L), 0.1);
        verify(empty, only()).apply(1L);
        verify(fallback, only()).getAsDouble();
    }
}
