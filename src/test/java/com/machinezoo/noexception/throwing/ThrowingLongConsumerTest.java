// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingLongConsumerTest {
	void takeThrowing(ThrowingLongConsumer functional) {
	}
	void takeNonThrowing(LongConsumer functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(v -> {
		});
		takeThrowing(v -> {
		});
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return ;
		});
		Throwable throwable = new IOException();
		takeThrowing(v -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return ;
		});
	}
}
