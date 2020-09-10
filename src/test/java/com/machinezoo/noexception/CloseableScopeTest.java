// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import org.junit.jupiter.api.*;

public class CloseableScopeTest {
	@Test
	@SuppressWarnings("resource")
	public void andThen() {
		List<String> executed = new ArrayList<>();
		CloseableScope inner = () -> executed.add("inner");
		assertThrows(NullPointerException.class, () -> inner.andThen(null));
		CloseableScope outer = inner.andThen(() -> executed.add("outer"));
		outer.close();
		assertEquals(new ArrayList<>(Arrays.asList("inner", "outer")), executed);
	}
	@Test
	@SuppressWarnings("resource")
	public void andThen_throwing() {
		AtomicBoolean executed = new AtomicBoolean();
		CloseableScope inner = () -> {
			throw new NumberFormatException();
		};
		CloseableScope outer = inner.andThen(() -> executed.set(true));
		assertThrows(NumberFormatException.class, outer::close);
		assertFalse(executed.get());
	}
	@Test
	@SuppressWarnings("resource")
	public void andFinally() {
		List<String> executed = new ArrayList<>();
		CloseableScope inner = () -> executed.add("inner");
		assertThrows(NullPointerException.class, () -> inner.andFinally(null));
		CloseableScope outer = inner.andFinally(() -> executed.add("outer"));
		outer.close();
		assertEquals(new ArrayList<>(Arrays.asList("inner", "outer")), executed);
	}
	@Test
	@SuppressWarnings("resource")
	public void andFinally_throwing() {
		AtomicBoolean executed = new AtomicBoolean();
		CloseableScope inner = () -> {
			throw new NumberFormatException();
		};
		CloseableScope outer = inner.andFinally(() -> executed.set(true));
		assertThrows(NumberFormatException.class, outer::close);
		assertTrue(executed.get());
	}
	@Test
	@SuppressWarnings("resource")
	public void andFinally_suppressed() {
		CloseableScope inner = () -> {
			throw new NumberFormatException();
		};
		CloseableScope outer = inner.andFinally(() -> {
			throw new NegativeArraySizeException();
		});
		NumberFormatException ex = assertThrows(NumberFormatException.class, outer::close);
		Throwable[] s = ex.getSuppressed();
		assertEquals(1, s.length);
		assertThat(s[0], is(instanceOf(NegativeArraySizeException.class)));
	}
}
