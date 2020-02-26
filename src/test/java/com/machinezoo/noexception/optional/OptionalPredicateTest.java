// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OptionalPredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(t -> OptionalBoolean.of(true)).test("input"));
		assertEquals(true, create(t -> OptionalBoolean.of(true)).orElse(false).test("input"));
		assertEquals(true, create(t -> OptionalBoolean.of(true)).orElseGet(() -> true).test("input"));
		assertEquals(OptionalBoolean.empty(), create(t -> OptionalBoolean.empty()).test("input"));
		assertEquals(false, create(t -> OptionalBoolean.empty()).orElse(false).test("input"));
		assertEquals(false, create(t -> OptionalBoolean.empty()).orElseGet(() -> false).test("input"));
	}
	private OptionalPredicate<String> create(OptionalPredicate<String> lambda) {
		return lambda;
	}
}
