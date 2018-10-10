// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;
import uk.org.lidalia.slf4jtest.*;

public class ExceptionsTest {
	TestLogger sharedLogger = TestLoggerFactory.getTestLogger(Exceptions.class);
	TestLogger customLogger = TestLoggerFactory.getTestLogger(ExceptionsTest.class);
	@Before public void initialize() {
		sharedLogger.clear();
		customLogger.clear();
	}
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
	@Test public void silence_interrupt() {
		Exceptions.silence().run(Exceptions.sneak().runnable(() -> {
			throw new InterruptedException();
		}));
		assertTrue(Thread.interrupted());
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
	@Test public void wrap_interrupt() {
		try {
			Exceptions.wrap().run(() -> {
				throw new InterruptedException();
			});
			fail();
		} catch (WrappedException e) {
			assertThat(e.getCause(), instanceOf(InterruptedException.class));
		}
		assertTrue(Thread.interrupted());
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
	@Test public void wrapIn_interrupt() {
		try {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new InterruptedException();
			});
			fail();
		} catch (CollectedException e) {
			assertThat(e.getCause(), instanceOf(InterruptedException.class));
		}
		assertTrue(Thread.interrupted());
	}
	@Test public void log_runtime() {
		Exceptions.log().run(() -> {
			throw new NumberFormatException();
		});
		assertEquals(1, sharedLogger.getLoggingEvents().size());
		LoggingEvent event = sharedLogger.getLoggingEvents().get(0);
		assertThat(event.getThrowable().orNull(), instanceOf(NumberFormatException.class));
		assertEquals("Caught exception", event.getMessage());
	}
	@Test public void log_error() {
		Exceptions.log().run(() -> {
			throw new IOError(new IOException());
		});
		assertEquals(1, sharedLogger.getLoggingEvents().size());
		assertThat(sharedLogger.getLoggingEvents().get(0).getThrowable().orNull(), instanceOf(IOError.class));
	}
	@Test public void log_interrupt() {
		Exceptions.log().run(Exceptions.sneak().runnable(() -> {
			throw new InterruptedException();
		}));
		assertTrue(Thread.interrupted());
		assertEquals(1, sharedLogger.getLoggingEvents().size());
		assertThat(sharedLogger.getLoggingEvents().get(0).getThrowable().orNull(), instanceOf(InterruptedException.class));
	}
	@Test public void logTo() {
		Exceptions.log(customLogger).run(() -> {
			throw new NumberFormatException();
		});
		assertEquals(0, sharedLogger.getLoggingEvents().size());
		assertEquals(1, customLogger.getLoggingEvents().size());
	}
	@Test public void logToWithMessage() {
		Exceptions.log(customLogger, "Commented exception").run(() -> {
			throw new NumberFormatException();
		});
		assertEquals(1, customLogger.getLoggingEvents().size());
		assertEquals("Commented exception", customLogger.getLoggingEvents().get(0).getMessage());
	}
}
