// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import org.junit.*;

public class OptionalBooleanSupplierTest {
	@Test public void conversions() {
		assertEquals(OptionalBoolean.of(true), create(() -> OptionalBoolean.of(true)).get());
		assertEquals(true, create(() -> OptionalBoolean.of(true)).orElse(false).getAsBoolean());
		assertEquals(true, create(() -> OptionalBoolean.of(true)).orElseGet(() -> false).getAsBoolean());
		assertEquals(OptionalBoolean.empty(), create(() -> OptionalBoolean.empty()).get());
		assertEquals(false, create(() -> OptionalBoolean.empty()).orElse(false).getAsBoolean());
		assertEquals(false, create(() -> OptionalBoolean.empty()).orElseGet(() -> false).getAsBoolean());
	}
	private OptionalBooleanSupplier create(OptionalBooleanSupplier lambda) {
		return lambda;
	}
}
