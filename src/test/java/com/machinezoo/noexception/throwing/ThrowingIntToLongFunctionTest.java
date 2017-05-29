// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntToLongFunctionTest {
	void takeThrowing(ThrowingIntToLongFunction functional) {
	}
	void takeNonThrowing(IntToLongFunction functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> 2L);
		takeThrowing(v -> 2L);
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2L;
		});
	}
}
