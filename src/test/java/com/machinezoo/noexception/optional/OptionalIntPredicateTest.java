// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OptionalIntPredicateTest {
	@Test
	public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(v -> OptionalBoolean.of(true)).test(1));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElse(false).test(1));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElseGet(() -> true).test(1));
		assertEquals(OptionalBoolean.empty(), create(v -> OptionalBoolean.empty()).test(1));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElse(false).test(1));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElseGet(() -> false).test(1));
	}
	private OptionalIntPredicate create(OptionalIntPredicate lambda) {
		return lambda;
	}
}
