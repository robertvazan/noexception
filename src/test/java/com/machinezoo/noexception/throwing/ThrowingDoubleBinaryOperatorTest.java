// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingDoubleBinaryOperatorTest {
	void takeThrowing(ThrowingDoubleBinaryOperator functional) {
	}
	void takeNonThrowing(DoubleBinaryOperator functional) {
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
