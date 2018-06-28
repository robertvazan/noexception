// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.throwing;

import java.io.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;

public class ThrowingToLongBiFunctionTest {
	void takeThrowing(ThrowingToLongBiFunction<String, String> functional) {
	}
	void takeNonThrowing(ToLongBiFunction<String, String> functional) {
	}
	@Test public void lambdas() {
		takeNonThrowing((t, u) -> 2L);
		takeThrowing((t, u) -> 2L);
		takeThrowing((t, u) -> {
			if (ThreadLocalRandom.current().nextBoolean())
				throw new IOException();
			else
				return 2L;
		});
	}
}
