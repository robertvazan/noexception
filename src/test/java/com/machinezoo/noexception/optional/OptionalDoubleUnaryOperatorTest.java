// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalDoubleUnaryOperatorTest {
	@Test public void conversions() {
		assertEquals(OptionalDouble.of(2.0), create(o -> OptionalDouble.of(2.0)).apply(1.0));
		assertEquals(2.0, create(o -> OptionalDouble.of(2.0)).orElse(3.0).applyAsDouble(1.0), 0.1);
		assertEquals(2.0, create(o -> OptionalDouble.of(2.0)).orElseGet(() -> 2.0).applyAsDouble(1.0), 0.1);
		assertEquals(OptionalDouble.empty(), create(o -> OptionalDouble.empty()).apply(1.0));
		assertEquals(3.0, create(o -> OptionalDouble.empty()).orElse(3.0).applyAsDouble(1.0), 0.1);
		assertEquals(3.0, create(o -> OptionalDouble.empty()).orElseGet(() -> 3.0).applyAsDouble(1.0), 0.1);
	}
	private OptionalDoubleUnaryOperator create(OptionalDoubleUnaryOperator lambda) {
		return lambda;
	}
}
