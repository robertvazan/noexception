// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackLongPredicateTest {
    @Test
    public void full() {
        OptionalLongPredicate full = mock(OptionalLongPredicate.class);
        when(full.test(1L)).thenReturn(OptionalBoolean.of(true));
        BooleanSupplier fallback = mock(BooleanSupplier.class);
        when(fallback.getAsBoolean()).thenReturn(false);
        assertEquals(true, new FallbackLongPredicate(full, fallback).test(1L));
        verify(full, only()).test(1L);
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        OptionalLongPredicate empty = mock(OptionalLongPredicate.class);
        when(empty.test(1L)).thenReturn(OptionalBoolean.empty());
        BooleanSupplier fallback = mock(BooleanSupplier.class);
        when(fallback.getAsBoolean()).thenReturn(false);
        assertEquals(false, new FallbackLongPredicate(empty, fallback).test(1L));
        verify(empty, only()).test(1L);
        verify(fallback, only()).getAsBoolean();
    }
}
