package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalFunctionTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(x -> Optional.of("value")).apply("input"));
		assertEquals("value", create(x -> Optional.of("value")).orElse("default").apply("input"));
		assertEquals("value", create(x -> Optional.of("value")).orElseGet(() -> "fallback").apply("input"));
		assertEquals(Optional.empty(), create(x -> Optional.empty()).apply("input"));
		assertEquals("default", create(x -> Optional.empty()).orElse("default").apply("input"));
		assertEquals("fallback", create(x -> Optional.empty()).orElseGet(() -> "fallback").apply("input"));
	}
	private OptionalFunction<String, String> create(OptionalFunction<String, String> lambda) {
		return lambda;
	}
}
