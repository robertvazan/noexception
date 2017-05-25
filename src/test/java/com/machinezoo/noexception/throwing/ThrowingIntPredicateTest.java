// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntPredicateTest {
	void takeThrowing(ThrowingIntPredicate functional) {
	}
	void takeNonThrowing(IntPredicate functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(x -> true);
		takeThrowing(x -> true);
		takeThrowing(x -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return true;
		});
	}
}
