// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalToDoubleBiFunctionTest {
	@Test public void conversions() {
		assertEquals(OptionalDouble.of(2.0), create((t, u) -> OptionalDouble.of(2.0)).apply("input1", "input2"));
		assertEquals(2.0, create((t, u) -> OptionalDouble.of(2.0)).orElse(3.0).applyAsDouble("input1", "input2"), 0.1);
		assertEquals(2.0, create((t, u) -> OptionalDouble.of(2.0)).orElseGet(() -> 2.0).applyAsDouble("input1", "input2"), 0.1);
		assertEquals(OptionalDouble.empty(), create((t, u) -> OptionalDouble.empty()).apply("input1", "input2"));
		assertEquals(3.0, create((t, u) -> OptionalDouble.empty()).orElse(3.0).applyAsDouble("input1", "input2"), 0.1);
		assertEquals(3.0, create((t, u) -> OptionalDouble.empty()).orElseGet(() -> 3.0).applyAsDouble("input1", "input2"), 0.1);
	}
	private OptionalToDoubleBiFunction<String, String> create(OptionalToDoubleBiFunction<String, String> lambda) {
		return lambda;
	}
}
