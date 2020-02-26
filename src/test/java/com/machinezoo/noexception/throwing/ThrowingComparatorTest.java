// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.*;

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
		Throwable throwable = new IOException();
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2;
		});
	}
}
