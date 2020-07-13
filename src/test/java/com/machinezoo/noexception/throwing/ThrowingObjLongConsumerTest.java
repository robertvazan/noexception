// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingObjLongConsumerTest {
	void takeThrowing(ThrowingObjLongConsumer<String> functional) {
	}
	void takeNonThrowing(ObjLongConsumer<String> functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing((t, v) -> {
		});
		takeThrowing((t, v) -> {
		});
		takeThrowing((t, v) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return;
		});
		Throwable throwable = new IOException();
		takeThrowing((t, v) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return;
		});
	}
}
