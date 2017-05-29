// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingBinaryOperatorTest {
	void takeThrowing(ThrowingBinaryOperator<String> functional) {
	}
	void takeNonThrowing(BinaryOperator<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((l, r) -> "value");
		takeThrowing((l, r) -> "value");
		takeThrowing((l, r) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return "value";
		});
	}
}
