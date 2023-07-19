// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalIntUnaryOperatorTest {
    @Test
    public void conversions() {
        assertEquals(OptionalInt.of(2), create(o -> OptionalInt.of(2)).apply(1));
        assertEquals(2, create(o -> OptionalInt.of(2)).orElse(3).applyAsInt(1));
        assertEquals(2, create(o -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt(1));
        assertEquals(OptionalInt.empty(), create(o -> OptionalInt.empty()).apply(1));
        assertEquals(3, create(o -> OptionalInt.empty()).orElse(3).applyAsInt(1));
        assertEquals(3, create(o -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt(1));
    }
    private OptionalIntUnaryOperator create(OptionalIntUnaryOperator lambda) {
        return lambda;
    }
}
