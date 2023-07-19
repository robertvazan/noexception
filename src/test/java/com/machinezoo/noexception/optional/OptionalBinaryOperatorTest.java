// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalBinaryOperatorTest {
    @Test
    public void conversions() {
        assertEquals(Optional.of("value"), create((l, r) -> Optional.of("value")).apply("input1", "input2"));
        assertEquals("value", create((l, r) -> Optional.of("value")).orElse("default").apply("input1", "input2"));
        assertEquals("value", create((l, r) -> Optional.of("value")).orElseGet(() -> "value").apply("input1", "input2"));
        assertEquals(Optional.empty(), create((l, r) -> Optional.empty()).apply("input1", "input2"));
        assertEquals("default", create((l, r) -> Optional.empty()).orElse("default").apply("input1", "input2"));
        assertEquals("default", create((l, r) -> Optional.empty()).orElseGet(() -> "default").apply("input1", "input2"));
    }
    private OptionalBinaryOperator<String> create(OptionalBinaryOperator<String> lambda) {
        return lambda;
    }
}
