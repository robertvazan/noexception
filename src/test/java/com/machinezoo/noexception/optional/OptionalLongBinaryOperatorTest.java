// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create((l, r) -> OptionalLong.of(2L)).apply(11L, 12L));
		assertEquals(2L, create((l, r) -> OptionalLong.of(2L)).orElse(3L).applyAsLong(11L, 12L));
		assertEquals(2L, create((l, r) -> OptionalLong.of(2L)).orElseGet(() -> 2L).applyAsLong(11L, 12L));
		assertEquals(OptionalLong.empty(), create((l, r) -> OptionalLong.empty()).apply(11L, 12L));
		assertEquals(3L, create((l, r) -> OptionalLong.empty()).orElse(3L).applyAsLong(11L, 12L));
		assertEquals(3L, create((l, r) -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong(11L, 12L));
	}
	private OptionalLongBinaryOperator create(OptionalLongBinaryOperator lambda) {
		return lambda;
	}
}
