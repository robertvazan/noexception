// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackToIntFunctionTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalToIntFunction<String> full = mock(OptionalToIntFunction.class);
        when(full.apply("input")).thenReturn(OptionalInt.of(2));
        IntSupplier fallback = mock(IntSupplier.class);
        when(fallback.getAsInt()).thenReturn(3);
        assertEquals(2, new FallbackToIntFunction<String>(full, fallback).applyAsInt("input"));
        verify(full, only()).apply("input");
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalToIntFunction<String> empty = mock(OptionalToIntFunction.class);
        when(empty.apply("input")).thenReturn(OptionalInt.empty());
        IntSupplier fallback = mock(IntSupplier.class);
        when(fallback.getAsInt()).thenReturn(3);
        assertEquals(3, new FallbackToIntFunction<String>(empty, fallback).applyAsInt("input"));
        verify(empty, only()).apply("input");
        verify(fallback, only()).getAsInt();
    }
}
