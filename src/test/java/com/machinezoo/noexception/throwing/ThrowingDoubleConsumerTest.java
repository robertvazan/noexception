// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingDoubleConsumerTest {
	void takeThrowing(ThrowingDoubleConsumer functional) {
	}
	void takeNonThrowing(DoubleConsumer functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing(v -> {
		});
		takeThrowing(v -> {
		});
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return;
		});
		Throwable throwable = new IOException();
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return;
		});
	}
}
