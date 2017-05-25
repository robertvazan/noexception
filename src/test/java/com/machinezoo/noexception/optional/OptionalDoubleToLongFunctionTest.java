// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleToLongFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create(x -> OptionalLong.of(2L)).apply(1));
		assertEquals(2L, create(x -> OptionalLong.of(2L)).orElse(3L).applyAsLong(1));
		assertEquals(2L, create(x -> OptionalLong.of(2L)).orElseGet(() -> 3L).applyAsLong(1));
		assertEquals(OptionalLong.empty(), create(x -> OptionalLong.empty()).apply(1));
		assertEquals(3L, create(x -> OptionalLong.empty()).orElse(3L).applyAsLong(1));
		assertEquals(3L, create(x -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong(1));
	}
	private OptionalDoubleToLongFunction create(OptionalDoubleToLongFunction lambda) {
		return lambda;
	}
}
