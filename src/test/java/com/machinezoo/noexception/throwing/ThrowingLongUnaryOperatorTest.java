// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingLongUnaryOperatorTest {
	void takeThrowing(ThrowingLongUnaryOperator functional) {
	}
	void takeNonThrowing(LongUnaryOperator functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing(o -> 2L);
		takeThrowing(o -> 2L);
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2L;
		});
		Throwable throwable = new IOException();
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2L;
		});
	}
}
