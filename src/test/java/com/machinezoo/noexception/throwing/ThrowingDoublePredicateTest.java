// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingDoublePredicateTest {
	void takeThrowing(ThrowingDoublePredicate functional) {
	}
	void takeNonThrowing(DoublePredicate functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> true);
		takeThrowing(v -> true);
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return true;
		});
	}
}
