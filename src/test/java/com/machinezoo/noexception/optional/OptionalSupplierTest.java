// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalSupplierTest {
	@Test public void conversions() {
		assertEquals(Optional.of("value"), create(() -> Optional.of("value")).get());
		assertEquals("value", create(() -> Optional.of("value")).orElse("default").get());
		assertEquals("value", create(() -> Optional.of("value")).orElseGet(() -> "value").get());
		assertEquals(Optional.empty(), create(() -> Optional.empty()).get());
		assertEquals("default", create(() -> Optional.empty()).orElse("default").get());
		assertEquals("default", create(() -> Optional.empty()).orElseGet(() -> "default").get());
	}
	private OptionalSupplier<String> create(OptionalSupplier<String> lambda) {
		return lambda;
	}
}
