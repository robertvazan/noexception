// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class DefaultFunctionTest {
    @Test
    public void full() {
        @SuppressWarnings("unchecked") OptionalFunction<String, String> full = mock(OptionalFunction.class);
        when(full.apply("input")).thenReturn(Optional.of("value"));
        assertEquals("value", new DefaultFunction<String, String>(full, "default").apply("input"));
        verify(full, only()).apply("input");
    }
    @Test
    public void empty() {
        @SuppressWarnings("unchecked") OptionalFunction<String, String> empty = mock(OptionalFunction.class);
        when(empty.apply("input")).thenReturn(Optional.empty());
        assertEquals("default", new DefaultFunction<String, String>(empty, "default").apply("input"));
        verify(empty, only()).apply("input");
    }
}
