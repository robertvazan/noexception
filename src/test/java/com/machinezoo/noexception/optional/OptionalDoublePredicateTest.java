// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import org.junit.*;

public class OptionalDoublePredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(v -> OptionalBoolean.of(true)).test(1.0));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElse(false).test(1.0));
		assertEquals(true, create(v -> OptionalBoolean.of(true)).orElseGet(() -> true).test(1.0));
		assertEquals(OptionalBoolean.empty(), create(v -> OptionalBoolean.empty()).test(1.0));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElse(false).test(1.0));
		assertEquals(false, create(v -> OptionalBoolean.empty()).orElseGet(() -> false).test(1.0));
	}
	private OptionalDoublePredicate create(OptionalDoublePredicate lambda) {
		return lambda;
	}
}
