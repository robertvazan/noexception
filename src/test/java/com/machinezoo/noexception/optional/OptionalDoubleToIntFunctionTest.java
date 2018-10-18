// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleToIntFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create(v -> OptionalInt.of(2)).apply(1.0));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElse(3).applyAsInt(1.0));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt(1.0));
		assertEquals(OptionalInt.empty(), create(v -> OptionalInt.empty()).apply(1.0));
		assertEquals(3, create(v -> OptionalInt.empty()).orElse(3).applyAsInt(1.0));
		assertEquals(3, create(v -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt(1.0));
	}
	private OptionalDoubleToIntFunction create(OptionalDoubleToIntFunction lambda) {
		return lambda;
	}
}
