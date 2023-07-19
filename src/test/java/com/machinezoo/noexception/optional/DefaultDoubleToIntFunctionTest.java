// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultDoubleToIntFunctionTest {
    @Test
    public void full() {
        OptionalDoubleToIntFunction full = mock(OptionalDoubleToIntFunction.class);
        when(full.apply(1.0)).thenReturn(OptionalInt.of(2));
        assertEquals(2, new DefaultDoubleToIntFunction(full, 3).applyAsInt(1.0));
        verify(full, only()).apply(1.0);
    }
    @Test
    public void empty() {
        OptionalDoubleToIntFunction empty = mock(OptionalDoubleToIntFunction.class);
        when(empty.apply(1.0)).thenReturn(OptionalInt.empty());
        assertEquals(3, new DefaultDoubleToIntFunction(empty, 3).applyAsInt(1.0));
        verify(empty, only()).apply(1.0);
    }
}
