// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalToIntFunctionTest {
	@Test
	public void conversions() {
		assertEquals(OptionalInt.of(2), create(v -> OptionalInt.of(2)).apply("input"));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElse(3).applyAsInt("input"));
		assertEquals(2, create(v -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt("input"));
		assertEquals(OptionalInt.empty(), create(v -> OptionalInt.empty()).apply("input"));
		assertEquals(3, create(v -> OptionalInt.empty()).orElse(3).applyAsInt("input"));
		assertEquals(3, create(v -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt("input"));
	}
	private OptionalToIntFunction<String> create(OptionalToIntFunction<String> lambda) {
		return lambda;
	}
}
