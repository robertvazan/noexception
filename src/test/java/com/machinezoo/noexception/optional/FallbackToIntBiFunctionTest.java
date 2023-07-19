// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackToIntBiFunctionTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalToIntBiFunction<String, String> full = mock(OptionalToIntBiFunction.class);
        when(full.apply("input1", "input2")).thenReturn(OptionalInt.of(2));
        IntSupplier fallback = mock(IntSupplier.class);
        when(fallback.getAsInt()).thenReturn(3);
        assertEquals(2, new FallbackToIntBiFunction<String, String>(full, fallback).applyAsInt("input1", "input2"));
        verify(full, only()).apply("input1", "input2");
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalToIntBiFunction<String, String> empty = mock(OptionalToIntBiFunction.class);
        when(empty.apply("input1", "input2")).thenReturn(OptionalInt.empty());
        IntSupplier fallback = mock(IntSupplier.class);
        when(fallback.getAsInt()).thenReturn(3);
        assertEquals(3, new FallbackToIntBiFunction<String, String>(empty, fallback).applyAsInt("input1", "input2"));
        verify(empty, only()).apply("input1", "input2");
        verify(fallback, only()).getAsInt();
    }
}
