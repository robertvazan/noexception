// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalToIntFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create(x -> OptionalInt.of(2)).apply("input"));
		assertEquals(2, create(x -> OptionalInt.of(2)).orElse(3).applyAsInt("input"));
		assertEquals(2, create(x -> OptionalInt.of(2)).orElseGet(() -> 3).applyAsInt("input"));
		assertEquals(OptionalInt.empty(), create(x -> OptionalInt.empty()).apply("input"));
		assertEquals(3, create(x -> OptionalInt.empty()).orElse(3).applyAsInt("input"));
		assertEquals(3, create(x -> OptionalInt.empty()).orElseGet(() -> 3).applyAsInt("input"));
	}
	private OptionalToIntFunction<String> create(OptionalToIntFunction<String> lambda) {
		return lambda;
	}
}
