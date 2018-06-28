// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingFunctionTest {
	void takeThrowing(ThrowingFunction<String, String> functional) {
	}
	void takeNonThrowing(Function<String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(t -> "value");
		takeThrowing(t -> "value");
		takeThrowing(t -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
