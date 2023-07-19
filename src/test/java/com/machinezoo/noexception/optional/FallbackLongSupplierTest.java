// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackLongSupplierTest {
    @Test
    public void full() {
        OptionalLongSupplier full = mock(OptionalLongSupplier.class);
        when(full.get()).thenReturn(OptionalLong.of(2L));
        LongSupplier fallback = mock(LongSupplier.class);
        when(fallback.getAsLong()).thenReturn(3L);
        assertEquals(2L, new FallbackLongSupplier(full, fallback).getAsLong());
        verify(full, only()).get();
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        OptionalLongSupplier empty = mock(OptionalLongSupplier.class);
        when(empty.get()).thenReturn(OptionalLong.empty());
        LongSupplier fallback = mock(LongSupplier.class);
        when(fallback.getAsLong()).thenReturn(3L);
        assertEquals(3L, new FallbackLongSupplier(empty, fallback).getAsLong());
        verify(empty, only()).get();
        verify(fallback, only()).getAsLong();
    }
}
