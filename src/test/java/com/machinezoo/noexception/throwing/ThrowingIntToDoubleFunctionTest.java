// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntToDoubleFunctionTest {
	void takeThrowing(ThrowingIntToDoubleFunction functional) {
	}
	void takeNonThrowing(IntToDoubleFunction functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> 2.0);
		takeThrowing(v -> 2.0);
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2.0;
		});
		Throwable throwable = new IOException();
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2.0;
		});
	}
}
