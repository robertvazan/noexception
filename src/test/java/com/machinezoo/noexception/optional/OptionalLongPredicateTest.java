// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class OptionalLongPredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(v -> OptionalBoolean.of(true)).test(1L));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElse(false).test(1L));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElseGet(() -> true).test(1L));
		assertEquals(OptionalBoolean.empty(), create(v -> OptionalBoolean.empty()).test(1L));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElse(false).test(1L));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElseGet(() -> false).test(1L));
	}
	private OptionalLongPredicate create(OptionalLongPredicate lambda) {
		return lambda;
	}
}
