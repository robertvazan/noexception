// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalToIntBiFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create((x, y) -> OptionalInt.of(2)).apply("input1", "input2"));
		assertEquals(2, create((x, y) -> OptionalInt.of(2)).orElse(3).applyAsInt("input1", "input2"));
		assertEquals(2, create((x, y) -> OptionalInt.of(2)).orElseGet(() -> 3).applyAsInt("input1", "input2"));
		assertEquals(OptionalInt.empty(), create((x, y) -> OptionalInt.empty()).apply("input1", "input2"));
		assertEquals(3, create((x, y) -> OptionalInt.empty()).orElse(3).applyAsInt("input1", "input2"));
		assertEquals(3, create((x, y) -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt("input1", "input2"));
	}
	private OptionalToIntBiFunction<String, String> create(OptionalToIntBiFunction<String, String> lambda) {
		return lambda;
	}
}
