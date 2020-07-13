// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.jupiter.api.*;

public class ThrowingIntBinaryOperatorTest {
	void takeThrowing(ThrowingIntBinaryOperator functional) {
	}
	void takeNonThrowing(IntBinaryOperator functional) {
	}
	@Test
	public void lambdas() {
		takeNonThrowing((l, r) -> 2);
		takeThrowing((l, r) -> 2);
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
		Throwable throwable = new IOException();
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2;
		});
	}
}
