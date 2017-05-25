// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create((x, y) -> OptionalLong.of(2L)).apply(11, 12));
		assertEquals(2L, create((x, y) -> OptionalLong.of(2L)).orElse(3L).applyAsLong(11, 12));
		assertEquals(2L, create((x, y) -> OptionalLong.of(2L)).orElseGet(() -> 3L).applyAsLong(11, 12));
		assertEquals(OptionalLong.empty(), create((x, y) -> OptionalLong.empty()).apply(11, 12));
		assertEquals(3L, create((x, y) -> OptionalLong.empty()).orElse(3L).applyAsLong(11, 12));
		assertEquals(3L, create((x, y) -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong(11, 12));
	}
	private OptionalLongBinaryOperator create(OptionalLongBinaryOperator lambda) {
		return lambda;
	}
}
