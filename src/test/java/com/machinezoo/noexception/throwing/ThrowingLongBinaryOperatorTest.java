// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingLongBinaryOperatorTest {
	void takeThrowing(ThrowingLongBinaryOperator functional) {
	}
	void takeNonThrowing(LongBinaryOperator functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((l, r) -> 2L);
		takeThrowing((l, r) -> 2L);
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2L;
		});
		Throwable throwable = new IOException();
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2L;
		});
	}
}
