// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleSupplierTest {
	@Test public void conversions() {
		assertEquals(OptionalDouble.of(2.0), create(() -> OptionalDouble.of(2.0)).get());
		assertEquals(2.0, create(() -> OptionalDouble.of(2.0)).orElse(3.0).getAsDouble(), 0.1);
		assertEquals(2.0, create(() -> OptionalDouble.of(2.0)).orElseGet(() -> 2.0).getAsDouble(), 0.1);
		assertEquals(OptionalDouble.empty(), create(() -> OptionalDouble.empty()).get());
		assertEquals(3.0, create(() -> OptionalDouble.empty()).orElse(3.0).getAsDouble(), 0.1);
		assertEquals(3.0, create(() -> OptionalDouble.empty()).orElseGet(() -> 3.0).getAsDouble(), 0.1);
	}
	private OptionalDoubleSupplier create(OptionalDoubleSupplier lambda) {
		return lambda;
	}
}
