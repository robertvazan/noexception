// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalIntBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create((x, y) -> OptionalInt.of(2)).apply(11, 12));
		assertEquals(2, create((x, y) -> OptionalInt.of(2)).orElse(3).applyAsInt(11, 12));
		assertEquals(2, create((x, y) -> OptionalInt.of(2)).orElseGet(() -> 3).applyAsInt(11, 12));
		assertEquals(OptionalInt.empty(), create((x, y) -> OptionalInt.empty()).apply(11, 12));
		assertEquals(3, create((x, y) -> OptionalInt.empty()).orElse(3).applyAsInt(11, 12));
		assertEquals(3, create((x, y) -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt(11, 12));
	}
	private OptionalIntBinaryOperator create(OptionalIntBinaryOperator lambda) {
		return lambda;
	}
}
