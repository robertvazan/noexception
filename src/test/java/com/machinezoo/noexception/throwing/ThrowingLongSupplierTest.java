// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingLongSupplierTest {
	void takeThrowing(ThrowingLongSupplier functional) {
	}
	void takeNonThrowing(LongSupplier functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing(() -> 2L);
		takeThrowing(() -> 2L);
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2L;
		});
		Throwable throwable = new IOException();
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2L;
		});
	}
}
