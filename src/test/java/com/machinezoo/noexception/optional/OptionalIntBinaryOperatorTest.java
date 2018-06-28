// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalIntBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create((l, r) -> OptionalInt.of(2)).apply(11, 12));
		assertEquals(2, create((l, r) -> OptionalInt.of(2)).orElse(3).applyAsInt(11, 12));
		assertEquals(2, create((l, r) -> OptionalInt.of(2)).orElseGet(() -> 2).applyAsInt(11, 12));
		assertEquals(OptionalInt.empty(), create((l, r) -> OptionalInt.empty()).apply(11, 12));
		assertEquals(3, create((l, r) -> OptionalInt.empty()).orElse(3).applyAsInt(11, 12));
		assertEquals(3, create((l, r) -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt(11, 12));
	}
	private OptionalIntBinaryOperator create(OptionalIntBinaryOperator lambda) {
		return lambda;
	}
}
