// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingDoubleUnaryOperatorTest {
	void takeThrowing(ThrowingDoubleUnaryOperator functional) {
	}
	void takeNonThrowing(DoubleUnaryOperator functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(o -> 2.0);
		takeThrowing(o -> 2.0);
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2.0;
		});
		Throwable throwable = new IOException();
		takeThrowing(o -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw throwable;
			else
				return 2.0;
		});
	}
}
