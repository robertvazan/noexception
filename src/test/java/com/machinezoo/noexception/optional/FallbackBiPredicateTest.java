// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class FallbackBiPredicateTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalBiPredicate<String, String> full = mock(OptionalBiPredicate.class);
        when(full.test("input1", "input2")).thenReturn(OptionalBoolean.of(true));
        BooleanSupplier fallback = mock(BooleanSupplier.class);
        when(fallback.getAsBoolean()).thenReturn(false);
        assertEquals(true, new FallbackBiPredicate<String, String>(full, fallback).test("input1", "input2"));
        verify(full, only()).test("input1", "input2");
        verifyNoMoreInteractions(fallback);
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalBiPredicate<String, String> empty = mock(OptionalBiPredicate.class);
        when(empty.test("input1", "input2")).thenReturn(OptionalBoolean.empty());
        BooleanSupplier fallback = mock(BooleanSupplier.class);
        when(fallback.getAsBoolean()).thenReturn(false);
        assertEquals(false, new FallbackBiPredicate<String, String>(empty, fallback).test("input1", "input2"));
        verify(empty, only()).test("input1", "input2");
        verify(fallback, only()).getAsBoolean();
    }
}
