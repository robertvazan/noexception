// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleFunctionTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(v -> Optional.of("value")).apply(1.0));
		assertEquals("value", create(v -> Optional.of("value")).orElse("default").apply(1.0));
		assertEquals("value", create(v -> Optional.of("value")).orElseGet(() -> "value").apply(1.0));
		assertEquals(Optional.empty(), create(v -> Optional.empty()).apply(1.0));
		assertEquals("default", create(v -> Optional.empty()).orElse("default").apply(1.0));
		assertEquals("default", create(v -> Optional.empty()).orElseGet(() -> "default").apply(1.0));
	}
	private OptionalDoubleFunction<String> create(OptionalDoubleFunction<String> lambda) {
		return lambda;
	}
}
