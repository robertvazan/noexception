// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultLongToIntFunctionTest {
    @Test
    public void full() {
        OptionalLongToIntFunction full = mock(OptionalLongToIntFunction.class);
        when(full.apply(1L)).thenReturn(OptionalInt.of(2));
        assertEquals(2, new DefaultLongToIntFunction(full, 3).applyAsInt(1L));
        verify(full, only()).apply(1L);
    }
    @Test
    public void empty() {
        OptionalLongToIntFunction empty = mock(OptionalLongToIntFunction.class);
        when(empty.apply(1L)).thenReturn(OptionalInt.empty());
        assertEquals(3, new DefaultLongToIntFunction(empty, 3).applyAsInt(1L));
        verify(empty, only()).apply(1L);
    }
}
