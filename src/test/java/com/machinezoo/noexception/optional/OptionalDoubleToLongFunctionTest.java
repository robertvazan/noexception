// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleToLongFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create(v -> OptionalLong.of(2L)).apply(1.0));
		assertEquals(2L, create(v -> OptionalLong.of(2L)).orElse(3L).applyAsLong(1.0));
		assertEquals(2L, create(v -> OptionalLong.of(2L)).orElseGet(() -> 2L).applyAsLong(1.0));
		assertEquals(OptionalLong.empty(), create(v -> OptionalLong.empty()).apply(1.0));
		assertEquals(3L, create(v -> OptionalLong.empty()).orElse(3L).applyAsLong(1.0));
		assertEquals(3L, create(v -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong(1.0));
	}
	private OptionalDoubleToLongFunction create(OptionalDoubleToLongFunction lambda) {
		return lambda;
	}
}
