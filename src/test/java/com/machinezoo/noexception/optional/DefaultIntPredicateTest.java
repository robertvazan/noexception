// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class DefaultIntPredicateTest {
    @Test
    public void full() {
        OptionalIntPredicate full = mock(OptionalIntPredicate.class);
        when(full.test(1)).thenReturn(OptionalBoolean.of(true));
        assertEquals(true, new DefaultIntPredicate(full, false).test(1));
        verify(full, only()).test(1);
    }
    @Test
    public void empty() {
        OptionalIntPredicate empty = mock(OptionalIntPredicate.class);
        when(empty.test(1)).thenReturn(OptionalBoolean.empty());
        assertEquals(false, new DefaultIntPredicate(empty, false).test(1));
        verify(empty, only()).test(1);
    }
}
