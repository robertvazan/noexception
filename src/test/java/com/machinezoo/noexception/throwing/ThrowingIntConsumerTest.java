// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingIntConsumerTest {
	void takeThrowing(ThrowingIntConsumer functional) {
	}
	void takeNonThrowing(IntConsumer functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing(x -> {});
		takeThrowing(x -> {});
		takeThrowing(x -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return ;
		});
	}
}
