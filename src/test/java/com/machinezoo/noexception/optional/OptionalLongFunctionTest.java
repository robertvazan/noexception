// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongFunctionTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(x -> Optional.of("value")).apply(1));
		assertEquals("value", create(x -> Optional.of("value")).orElse("default").apply(1));
		assertEquals("value", create(x -> Optional.of("value")).orElseGet(() -> "fallback").apply(1));
		assertEquals(Optional.empty(), create(x -> Optional.empty()).apply(1));
		assertEquals("default", create(x -> Optional.empty()).orElse("default").apply(1));
		assertEquals("fallback", create(x -> Optional.empty()).orElseGet(() -> "fallback").apply(1));
	}
	private OptionalLongFunction<String> create(OptionalLongFunction<String> lambda) {
		return lambda;
	}
}
