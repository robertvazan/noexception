// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class OptionalLongSupplierTest {
	@Test public void conversions() {
		assertEquals(OptionalLong.of(2L), create(() -> OptionalLong.of(2L)).get());
		assertEquals(2L, create(() -> OptionalLong.of(2L)).orElse(3L).getAsLong());
		assertEquals(2L, create(() -> OptionalLong.of(2L)).orElseGet(() -> 2L).getAsLong());
		assertEquals(OptionalLong.empty(), create(() -> OptionalLong.empty()).get());
		assertEquals(3L, create(() -> OptionalLong.empty()).orElse(3L).getAsLong());
		assertEquals(3L, create(() -> OptionalLong.empty()).orElseGet(() -> 3L).getAsLong());
	}
	private OptionalLongSupplier create(OptionalLongSupplier lambda) {
		return lambda;
	}
}
