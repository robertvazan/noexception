// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class DefaultBiPredicateTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalBiPredicate<String, String> full = mock(OptionalBiPredicate.class);
        when(full.test("input1", "input2")).thenReturn(OptionalBoolean.of(true));
        assertEquals(true, new DefaultBiPredicate<String, String>(full, false).test("input1", "input2"));
        verify(full, only()).test("input1", "input2");
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalBiPredicate<String, String> empty = mock(OptionalBiPredicate.class);
        when(empty.test("input1", "input2")).thenReturn(OptionalBoolean.empty());
        assertEquals(false, new DefaultBiPredicate<String, String>(empty, false).test("input1", "input2"));
        verify(empty, only()).test("input1", "input2");
    }
}
