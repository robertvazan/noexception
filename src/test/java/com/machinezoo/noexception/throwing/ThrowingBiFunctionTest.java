// Part of NoException: https://noexception.machinezoo.com
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
		takeNonThrowing((x, y) -> "value");
		takeThrowing((x, y) -> "value");
		takeThrowing((x, y) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
