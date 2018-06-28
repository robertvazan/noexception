// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongToIntFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create(v -> OptionalInt.of(2)).apply(1L));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElse(3).applyAsInt(1L));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt(1L));
		assertEquals(OptionalInt.empty(), create(v -> OptionalInt.empty()).apply(1L));
		assertEquals(3, create(v -> OptionalInt.empty()).orElse(3).applyAsInt(1L));
		assertEquals(3, create(v -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt(1L));
	}
	private OptionalLongToIntFunction create(OptionalLongToIntFunction lambda) {
		return lambda;
	}
}
