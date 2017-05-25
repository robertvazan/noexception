// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingSupplierTest {
	void takeThrowing(ThrowingSupplier<String> functional) {
	}
	void takeNonThrowing(Supplier<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(() -> "value");
		takeThrowing(() -> "value");
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
