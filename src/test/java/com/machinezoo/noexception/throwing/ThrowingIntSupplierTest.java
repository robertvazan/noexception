// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntSupplierTest {
	void takeThrowing(ThrowingIntSupplier functional) {
	}
	void takeNonThrowing(IntSupplier functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(() -> 2);
		takeThrowing(() -> 2);
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
	}
}
