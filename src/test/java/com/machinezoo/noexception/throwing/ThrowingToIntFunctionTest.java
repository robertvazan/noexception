// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingToIntFunctionTest {
	void takeThrowing(ThrowingToIntFunction<String> functional) {
	}
	void takeNonThrowing(ToIntFunction<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> 2);
		takeThrowing(v -> 2);
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
	}
}
