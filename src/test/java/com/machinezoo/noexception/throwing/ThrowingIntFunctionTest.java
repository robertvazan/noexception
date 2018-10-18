// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntFunctionTest {
	void takeThrowing(ThrowingIntFunction<String> functional) {
	}
	void takeNonThrowing(IntFunction<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> "value");
		takeThrowing(v -> "value");
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
