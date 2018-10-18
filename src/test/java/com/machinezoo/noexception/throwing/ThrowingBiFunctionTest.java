// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingBiFunctionTest {
	void takeThrowing(ThrowingBiFunction<String, String, String> functional) {
	}
	void takeNonThrowing(BiFunction<String, String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((t, u) -> "value");
		takeThrowing((t, u) -> "value");
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
