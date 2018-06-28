// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingObjIntConsumerTest {
	void takeThrowing(ThrowingObjIntConsumer<String> functional) {
	}
	void takeNonThrowing(ObjIntConsumer<String> functional) {
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
