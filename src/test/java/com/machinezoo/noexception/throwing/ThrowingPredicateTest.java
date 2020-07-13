// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingPredicateTest {
	void takeThrowing(ThrowingPredicate<String> functional) {
	}
	void takeNonThrowing(Predicate<String> functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing(t -> true);
		takeThrowing(t -> true);
		takeThrowing(t -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return true;
		});
		Throwable throwable = new IOException();
		takeThrowing(t -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return true;
		});
	}
}
