// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import org.junit.*;

public class ThrowingRunnableTest {
	void takeThrowing(ThrowingRunnable functional) {
	}
	void takeNonThrowing(Runnable functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(() -> {});
		takeThrowing(() -> {});
		takeThrowing(() -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return ;
		});
	}
}
