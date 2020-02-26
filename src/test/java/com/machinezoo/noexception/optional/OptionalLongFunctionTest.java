// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalLongFunctionTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(v -> Optional.of("value")).apply(1L));
		assertEquals("value", create(v -> Optional.of("value")).orElse("default").apply(1L));
		assertEquals("value", create(v -> Optional.of("value")).orElseGet(() -> "value").apply(1L));
		assertEquals(Optional.empty(), create(v -> Optional.empty()).apply(1L));
		assertEquals("default", create(v -> Optional.empty()).orElse("default").apply(1L));
		assertEquals("default", create(v -> Optional.empty()).orElseGet(() -> "default").apply(1L));
	}
	private OptionalLongFunction<String> create(OptionalLongFunction<String> lambda) {
		return lambda;
	}
}
