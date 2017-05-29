// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalDouble.of(2.0), create((l, r) -> OptionalDouble.of(2.0)).apply(1.1, 1.2));
		assertEquals(2.0, create((l, r) -> OptionalDouble.of(2.0)).orElse(3.0).applyAsDouble(1.1, 1.2), 0.1);
		assertEquals(2.0, create((l, r) -> OptionalDouble.of(2.0)).orElseGet(() -> 2.0).applyAsDouble(1.1, 1.2), 0.1);
		assertEquals(OptionalDouble.empty(), create((l, r) -> OptionalDouble.empty()).apply(1.1, 1.2));
		assertEquals(3.0, create((l, r) -> OptionalDouble.empty()).orElse(3.0).applyAsDouble(1.1, 1.2), 0.1);
		assertEquals(3.0, create((l, r) -> OptionalDouble.empty()).orElseGet(() -> 3.0).applyAsDouble(1.1, 1.2), 0.1);
	}
	private OptionalDoubleBinaryOperator create(OptionalDoubleBinaryOperator lambda) {
		return lambda;
	}
}
