// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalBinaryOperatorTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create((x, y) -> Optional.of("value")).apply("input1", "input2"));
		assertEquals("value", create((x, y) -> Optional.of("value")).orElse("default").apply("input1", "input2"));
		assertEquals("value", create((x, y) -> Optional.of("value")).orElseGet(() -> "fallback").apply("input1", "input2"));
		assertEquals(Optional.empty(), create((x, y) -> Optional.empty()).apply("input1", "input2"));
		assertEquals("default", create((x, y) -> Optional.empty()).orElse("default").apply("input1", "input2"));
		assertEquals("fallback", create((x, y) -> Optional.empty()).orElseGet(() -> "fallback").apply("input1", "input2"));
	}
	private OptionalBinaryOperator<String> create(OptionalBinaryOperator<String> lambda) {
		return lambda;
	}
}
