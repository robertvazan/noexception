// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.junit.*;

public class ThrowingComparatorTest {
	void takeThrowing(ThrowingComparator<String> functional) {
	}
	void takeNonThrowing(Comparator<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((l, r) -> 2);
		takeThrowing((l, r) -> 2);
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
	}
}
