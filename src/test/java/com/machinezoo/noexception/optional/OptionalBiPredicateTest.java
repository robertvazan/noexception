// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import org.junit.*;

public class OptionalBiPredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create((t, u) -> OptionalBoolean.of(true)).test("input1", "input2"));
		assertEquals(true, create((t, u) -> OptionalBoolean.of(true)).orElse(false).test("input1", "input2"));
		assertEquals(true, create((t, u) -> OptionalBoolean.of(true)).orElseGet(() -> true).test("input1", "input2"));
		assertEquals(OptionalBoolean.empty(), create((t, u) -> OptionalBoolean.empty()).test("input1", "input2"));
		assertEquals(false, create((t, u) -> OptionalBoolean.empty()).orElse(false).test("input1", "input2"));
		assertEquals(false, create((t, u) -> OptionalBoolean.empty()).orElseGet(() -> false).test("input1", "input2"));
	}
	private OptionalBiPredicate<String, String> create(OptionalBiPredicate<String, String> lambda) {
		return lambda;
	}
}
