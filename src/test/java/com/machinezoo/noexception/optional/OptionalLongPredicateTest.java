// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongPredicateTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(x -> OptionalBoolean.of(true)).test(1));
		assertEquals(true, create(x -> OptionalBoolean.of(true)).orElse(false).test(1));
		assertEquals(true, create(x -> OptionalBoolean.of(true)).orElseGet(() -> false).test(1));
		assertEquals(OptionalBoolean.empty(), create(x -> OptionalBoolean.empty()).test(1));
		assertEquals(false, create(x -> OptionalBoolean.empty()).orElse(false).test(1));
		assertEquals(false, create(x -> OptionalBoolean.empty()).orElseGet(() -> false).test(1));
	}
	private OptionalLongPredicate create(OptionalLongPredicate lambda) {
		return lambda;
	}
}
