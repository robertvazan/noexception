// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingObjDoubleConsumerTest {
	void takeThrowing(ThrowingObjDoubleConsumer<String> functional) {
	}
	void takeNonThrowing(ObjDoubleConsumer<String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((t, v) -> {
		});
		takeThrowing((t, v) -> {
		});
		takeThrowing((t, v) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return ;
		});
	}
}
