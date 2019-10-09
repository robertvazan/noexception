// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntUnaryOperatorTest {
	void takeThrowing(ThrowingIntUnaryOperator functional) {
	}
	void takeNonThrowing(IntUnaryOperator functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(o -> 2);
		takeThrowing(o -> 2);
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2;
		});
		Throwable throwable = new IOException();
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2;
		});
	}
}
