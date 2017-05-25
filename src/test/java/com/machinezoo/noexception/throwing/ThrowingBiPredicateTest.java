// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingBiPredicateTest {
	void takeThrowing(ThrowingBiPredicate<String, String> functional) {
	}
	void takeNonThrowing(BiPredicate<String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((x, y) -> true);
		takeThrowing((x, y) -> true);
		takeThrowing((x, y) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return true;
		});
	}
}
