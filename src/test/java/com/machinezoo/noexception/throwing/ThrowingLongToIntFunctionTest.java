// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingLongToIntFunctionTest {
	void takeThrowing(ThrowingLongToIntFunction functional) {
	}
	void takeNonThrowing(LongToIntFunction functional) {
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
		Throwable throwable = new IOException();
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2;
		});
	}
}
