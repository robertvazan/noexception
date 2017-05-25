// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalPredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(x -> OptionalBoolean.of(true)).test("input"));
		assertEquals(true, create(x -> OptionalBoolean.of(true)).orElse(false).test("input"));
		assertEquals(true, create(x -> OptionalBoolean.of(true)).orElseGet(() -> false).test("input"));
		assertEquals(OptionalBoolean.empty(), create(x -> OptionalBoolean.empty()).test("input"));
		assertEquals(false, create(x -> OptionalBoolean.empty()).orElse(false).test("input"));
		assertEquals(false, create(x -> OptionalBoolean.empty()).orElseGet(() -> false).test("input"));
	}
	private OptionalPredicate<String> create(OptionalPredicate<String> lambda) {
		return lambda;
	}
}
