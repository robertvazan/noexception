// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingBooleanSupplierTest {
	void takeThrowing(ThrowingBooleanSupplier functional) {
	}
	void takeNonThrowing(BooleanSupplier functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing(() -> true);
		takeThrowing(() -> true);
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return true;
		});
		Throwable throwable = new IOException();
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return true;
		});
	}
}
