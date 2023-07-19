// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackToDoubleFunctionTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalToDoubleFunction<String> full = mock(OptionalToDoubleFunction.class);
        when(full.apply("input")).thenReturn(OptionalDouble.of(2.0));
        DoubleSupplier fallback = mock(DoubleSupplier.class);
        when(fallback.getAsDouble()).thenReturn(3.0);
        assertEquals(2.0, new FallbackToDoubleFunction<String>(full, fallback).applyAsDouble("input"), 0.1);
        verify(full, only()).apply("input");
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalToDoubleFunction<String> empty = mock(OptionalToDoubleFunction.class);
        when(empty.apply("input")).thenReturn(OptionalDouble.empty());
        DoubleSupplier fallback = mock(DoubleSupplier.class);
        when(fallback.getAsDouble()).thenReturn(3.0);
        assertEquals(3.0, new FallbackToDoubleFunction<String>(empty, fallback).applyAsDouble("input"), 0.1);
        verify(empty, only()).apply("input");
        verify(fallback, only()).getAsDouble();
    }
}
