// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackUnaryOperatorTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalUnaryOperator<String> full = mock(OptionalUnaryOperator.class);
        when(full.apply("input")).thenReturn(Optional.of("value"));
        @SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
        when(fallback.get()).thenReturn("default");
        assertEquals("value", new FallbackUnaryOperator<String>(full, fallback).apply("input"));
        verify(full, only()).apply("input");
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalUnaryOperator<String> empty = mock(OptionalUnaryOperator.class);
        when(empty.apply("input")).thenReturn(Optional.empty());
        @SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
        when(fallback.get()).thenReturn("default");
        assertEquals("default", new FallbackUnaryOperator<String>(empty, fallback).apply("input"));
        verify(empty, only()).apply("input");
        verify(fallback, only()).get();
    }
}
