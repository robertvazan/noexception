// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalFunctionTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(t -> Optional.of("value")).apply("input"));
		assertEquals("value", create(t -> Optional.of("value")).orElse("default").apply("input"));
		assertEquals("value", create(t -> Optional.of("value")).orElseGet(() -> "value").apply("input"));
		assertEquals(Optional.empty(), create(t -> Optional.empty()).apply("input"));
		assertEquals("default", create(t -> Optional.empty()).orElse("default").apply("input"));
		assertEquals("default", create(t -> Optional.empty()).orElseGet(() -> "default").apply("input"));
	}
	private OptionalFunction<String, String> create(OptionalFunction<String, String> lambda) {
		return lambda;
	}
}
