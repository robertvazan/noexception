// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultLongToDoubleFunctionTest {
    @Test
    public void full() {
        OptionalLongToDoubleFunction full = mock(OptionalLongToDoubleFunction.class);
        when(full.apply(1L)).thenReturn(OptionalDouble.of(2.0));
        assertEquals(2.0, new DefaultLongToDoubleFunction(full, 3.0).applyAsDouble(1L), 0.1);
        verify(full, only()).apply(1L);
    }
    @Test
    public void empty() {
        OptionalLongToDoubleFunction empty = mock(OptionalLongToDoubleFunction.class);
        when(empty.apply(1L)).thenReturn(OptionalDouble.empty());
        assertEquals(3.0, new DefaultLongToDoubleFunction(empty, 3.0).applyAsDouble(1L), 0.1);
        verify(empty, only()).apply(1L);
    }
}
