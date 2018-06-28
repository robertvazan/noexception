// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalToLongFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create(v -> OptionalLong.of(2L)).apply("input"));
		assertEquals(2L, create(v -> OptionalLong.of(2L)).orElse(3L).applyAsLong("input"));
		assertEquals(2L, create(v -> OptionalLong.of(2L)).orElseGet(() -> 2L).applyAsLong("input"));
		assertEquals(OptionalLong.empty(), create(v -> OptionalLong.empty()).apply("input"));
		assertEquals(3L, create(v -> OptionalLong.empty()).orElse(3L).applyAsLong("input"));
		assertEquals(3L, create(v -> OptionalLong.empty()).orElseGet(() -> 3L).applyAsLong("input"));
	}
	private OptionalToLongFunction<String> create(OptionalToLongFunction<String> lambda) {
		return lambda;
	}
}
