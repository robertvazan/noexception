// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

public class OptionalComparatorTest {
	@Test public void conversions() {
		assertEquals(OptionalInt.of(2), create((l, r) -> OptionalInt.of(2)).compare("input1", "input2"));
		assertEquals(2, create((l, r) -> OptionalInt.of(2)).orElse(3).compare("input1", "input2"));
		assertEquals(2, create((l, r) -> OptionalInt.of(2)).orElseGet(() -> 2).compare("input1", "input2"));
		assertEquals(OptionalInt.empty(), create((l, r) -> OptionalInt.empty()).compare("input1", "input2"));
		assertEquals(3, create((l, r) -> OptionalInt.empty()).orElse(3).compare("input1", "input2"));
		assertEquals(3, create((l, r) -> OptionalInt.empty()).orElseGet(() -> 3).compare("input1", "input2"));
	}
	private OptionalComparator<String> create(OptionalComparator<String> lambda) {
		return lambda;
	}
}
