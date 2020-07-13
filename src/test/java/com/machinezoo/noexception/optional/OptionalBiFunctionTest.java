// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalBiFunctionTest {
	@Test
	public void conversions() {
		assertEquals(Optional.of("value"), create((t, u) -> Optional.of("value")).apply("input1", "input2"));
		assertEquals("value", create((t, u) -> Optional.of("value")).orElse("default").apply("input1", "input2"));
		assertEquals("value", create((t, u) -> Optional.of("value")).orElseGet(() -> "value").apply("input1", "input2"));
		assertEquals(Optional.empty(), create((t, u) -> Optional.empty()).apply("input1", "input2"));
		assertEquals("default", create((t, u) -> Optional.empty()).orElse("default").apply("input1", "input2"));
		assertEquals("default", create((t, u) -> Optional.empty()).orElseGet(() -> "default").apply("input1", "input2"));
	}
	private OptionalBiFunction<String, String, String> create(OptionalBiFunction<String, String, String> lambda) {
		return lambda;
	}
}
