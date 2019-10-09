// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingBiConsumerTest {
	void takeThrowing(ThrowingBiConsumer<String, String> functional) {
	}
	void takeNonThrowing(BiConsumer<String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((t, u) -> {
		});
		takeThrowing((t, u) -> {
		});
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return ;
		});
		Throwable throwable = new IOException();
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return ;
		});
	}
}
