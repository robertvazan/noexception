// Part of NoException: https://noexception.machinezoo.com
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
		takeNonThrowing(() -> 0);
		takeThrowing(() -> 0);
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 0;
		});
	}
}
