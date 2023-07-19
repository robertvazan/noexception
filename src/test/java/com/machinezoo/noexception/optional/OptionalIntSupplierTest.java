// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalIntSupplierTest {
    @Test
    public void conversions() {
        assertEquals(OptionalInt.of(2), create(() -> OptionalInt.of(2)).get());
        assertEquals(2, create(() -> OptionalInt.of(2)).orElse(3).getAsInt());
        assertEquals(2, create(() -> OptionalInt.of(2)).orElseGet(() -> 2).getAsInt());
        assertEquals(OptionalInt.empty(), create(() -> OptionalInt.empty()).get());
        assertEquals(3, create(() -> OptionalInt.empty()).orElse(3).getAsInt());
        assertEquals(3, create(() -> OptionalInt.empty()).orElseGet(() -> 3).getAsInt());
    }
    private OptionalIntSupplier create(OptionalIntSupplier lambda) {
        return lambda;
    }
}
