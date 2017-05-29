// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongUnaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create(o -> OptionalLong.of(2L)).apply(1L));
		assertEquals(2L, create(o -> OptionalLong.of(2L)).orElse(3L).applyAsLong(1L));
		assertEquals(2L, create(o -> OptionalLong.of(2L)).orElseGet(() -> 2L).applyAsLong(1L));
		assertEquals(OptionalLong.empty(), create(o -> OptionalLong.empty()).apply(1L));
		assertEquals(3L, create(o -> OptionalLong.empty()).orElse(3L).applyAsLong(1L));
		assertEquals(3L, create(o -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong(1L));
	}
	private OptionalLongUnaryOperator create(OptionalLongUnaryOperator lambda) {
		return lambda;
	}
}
