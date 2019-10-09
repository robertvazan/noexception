// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingToIntBiFunctionTest {
	void takeThrowing(ThrowingToIntBiFunction<String, String> functional) {
	}
	void takeNonThrowing(ToIntBiFunction<String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((t, u) -> 2);
		takeThrowing((t, u) -> 2);
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
		Throwable throwable = new IOException();
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2;
		});
	}
}
