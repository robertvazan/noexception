// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingLongFunctionTest {
	void takeThrowing(ThrowingLongFunction<String> functional) {
	}
	void takeNonThrowing(LongFunction<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(x -> "value");
		takeThrowing(x -> "value");
		takeThrowing(x -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}