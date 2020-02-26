// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalToIntBiFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create((t, u) -> OptionalInt.of(2)).apply("input1", "input2"));
		assertEquals(2, create((t, u) -> OptionalInt.of(2)).orElse(3).applyAsInt("input1", "input2"));
		assertEquals(2, create((t, u) -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt("input1", "input2"));
		assertEquals(OptionalInt.empty(), create((t, u) -> OptionalInt.empty()).apply("input1", "input2"));
		assertEquals(3, create((t, u) -> OptionalInt.empty()).orElse(3).applyAsInt("input1", "input2"));
		assertEquals(3, create((t, u) -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt("input1", "input2"));
	}
	private OptionalToIntBiFunction<String, String> create(OptionalToIntBiFunction<String, String> lambda) {
		return lambda;
	}
}
