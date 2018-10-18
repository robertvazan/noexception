// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingDoubleSupplierTest {
	void takeThrowing(ThrowingDoubleSupplier functional) {
	}
	void takeNonThrowing(DoubleSupplier functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(() -> 2.0);
		takeThrowing(() -> 2.0);
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2.0;
		});
	}
}
