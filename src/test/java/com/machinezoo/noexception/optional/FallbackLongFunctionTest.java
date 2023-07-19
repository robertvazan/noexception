// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackLongFunctionTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalLongFunction<String> full = mock(OptionalLongFunction.class);
        when(full.apply(1L)).thenReturn(Optional.of("value"));
        @SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
        when(fallback.get()).thenReturn("default");
        assertEquals("value", new FallbackLongFunction<String>(full, fallback).apply(1L));
        verify(full, only()).apply(1L);
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalLongFunction<String> empty = mock(OptionalLongFunction.class);
        when(empty.apply(1L)).thenReturn(Optional.empty());
        @SuppressWarnings("unchecked") Supplier<String> fallback = mock(Supplier.class);
        when(fallback.get()).thenReturn("default");
        assertEquals("default", new FallbackLongFunction<String>(empty, fallback).apply(1L));
        verify(empty, only()).apply(1L);
        verify(fallback, only()).get();
    }
}
