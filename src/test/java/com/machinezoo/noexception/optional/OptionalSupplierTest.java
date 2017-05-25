// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalSupplierTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(() -> Optional.of("value")).get());
		assertEquals("value", create(() -> Optional.of("value")).orElse("default").get());
		assertEquals("value", create(() -> Optional.of("value")).orElseGet(() -> "fallback").get());
		assertEquals(Optional.empty(), create(() -> Optional.empty()).get());
		assertEquals("default", create(() -> Optional.empty()).orElse("default").get());
		assertEquals("fallback", create(() -> Optional.empty()).orElseGet(() -> "fallback").get());
	}
	private OptionalSupplier<String> create(OptionalSupplier<String> lambda) {
		return lambda;
	}
}
