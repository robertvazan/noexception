// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.io.*;
import org.junit.*;
import org.slf4j.*;

public class ExceptionsTest {
	@Test(expected = NumberFormatException.class) public void pass_runtime() {
		Exceptions.pass().run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test(expected = IOError.class) public void pass_error() {
		Exceptions.pass().run(() -> {
			throw new IOError(new IOException());
		});
	}

	@Test public void silence_runtime() {
		Exceptions.silence().run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test public void silence_error() {
		Exceptions.silence().run(() -> {
			throw new IOError(new IOException());
		});
	}

	@Test(expected = NumberFormatException.class) public void sneak_runtime() {
		Exceptions.sneak().run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test(expected = IOError.class) public void sneak_error() {
		Exceptions.sneak().run(() -> {
			throw new IOError(new IOException());
		});
	}
	@Test(expected = IOException.class) public void sneak_checked() {
		Exceptions.sneak().run(() -> {
			throw new IOException();
		});
	}

	@Test(expected = NumberFormatException.class) public void wrap_runtime() {
		Exceptions.wrap().run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test(expected = IOError.class) public void wrap_error() {
		Exceptions.wrap().run(() -> {
			throw new IOError(new IOException());
		});
	}
	@Test public void wrap_checked() {
		try {
			Exceptions.wrap().run(() -> {
				throw new IOException();
			});
			fail();
		} catch (WrappedException e) {
			assertThat(e.getCause(), instanceOf(IOException.class));
		}
	}
	@Test public void wrap_InterruptedException() {
		try {
			Exceptions.wrap().run(() -> {
				throw new InterruptedException();
			});
			fail();
		} catch (WrappedException e) {
			assertThat(e.getCause(), instanceOf(InterruptedException.class));
			checkAndClearInterruptStatus();
		}
	}

	@Test(expected = NumberFormatException.class) public void wrapIn_runtime() {
		Exceptions.wrap(CollectedException::new).run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test(expected = IOError.class) public void wrapIn_error() {
		Exceptions.wrap(CollectedException::new).run(() -> {
			throw new IOError(new IOException());
		});
	}
	@Test public void wrapIn_checked() {
		try {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new IOException();
			});
			fail();
		} catch (CollectedException e) {
			assertThat(e.getCause(), instanceOf(IOException.class));
		}
	}
	@Test(expected = WrappedException.class) public void wrapIn_checked_not_mapped() {
		Exceptions.wrap(e -> null).run(() -> {
			throw new IOException();
		});
	}
	@Test public void wrapIn_InterruptedException() {
		try {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new InterruptedException();
			});
			fail();
		} catch (CollectedException e) {
			assertThat(e.getCause(), instanceOf(InterruptedException.class));
			checkAndClearInterruptStatus();
		}
	}

	@Test public void log_runtime() {
		Exceptions.log().run(() -> {
			throw new NumberFormatException();
		});
	}
	@Test public void log_error() {
		Exceptions.log().run(() -> {
			throw new IOError(new IOException());
		});
	}

	@Test public void logTo() {
		Logger logger = mock(Logger.class);
		Exceptions.log(logger).run(() -> {
			throw new NumberFormatException();
		});
		verify(logger, only()).error(eq("Caught exception"), any(NumberFormatException.class));
	}

	@Test public void logTWithMessage() {
		Logger logger = mock(Logger.class);
		Exceptions.log(logger, "Commented exception").run(() -> {
			throw new NumberFormatException();
		});
		verify(logger, only()).error(eq("Commented exception"), any(NumberFormatException.class));
	}

	private static void checkAndClearInterruptStatus() {
		assertThat(
				"Interrupt status is not restored",
				Thread.interrupted(), // We must clear interrupt status
				is(true));
	}
}
