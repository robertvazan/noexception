// Part of NoException: https://noexception.machinezoo.com
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
		takeNonThrowing((x, y) -> 0);
		takeThrowing((x, y) -> 0);
		takeThrowing((x, y) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 0;
		});
	}
}
