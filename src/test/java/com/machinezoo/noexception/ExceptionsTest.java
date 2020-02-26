// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.*;
import uk.org.lidalia.slf4jtest.*;

public class ExceptionsTest {
	TestLogger sharedLogger = TestLoggerFactory.getTestLogger(Exceptions.class);
	TestLogger customLogger = TestLoggerFactory.getTestLogger(ExceptionsTest.class);
	@BeforeEach public void initialize() {
		sharedLogger.clear();
		customLogger.clear();
	}
	@Test public void pass_runtime() {
		assertThrows(NumberFormatException.class, () -> {
			Exceptions.pass().run(() -> {
				throw new NumberFormatException();
			});
		});
	}
	@Test public void pass_error() {
		assertThrows(IOError.class, () -> {
			Exceptions.pass().run(() -> {
				throw new IOError(new IOException());
			});
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
	@Test public void sneak_runtime() {
		assertThrows(NumberFormatException.class, () -> {
			Exceptions.sneak().run(() -> {
				throw new NumberFormatException();
			});
		});
	}
	@Test public void sneak_error() {
		assertThrows(IOError.class, () -> {
			Exceptions.sneak().run(() -> {
				throw new IOError(new IOException());
			});
		});
	}
	@Test public void sneak_checked() {
		assertThrows(IOException.class, () -> {
			Exceptions.sneak().run(() -> {
				throw new IOException();
			});
		});
	}
	@Test public void wrap_runtime() {
		assertThrows(NumberFormatException.class, () -> {
			Exceptions.wrap().run(() -> {
				throw new NumberFormatException();
			});
		});
	}
	@Test public void wrap_error() {
		assertThrows(IOError.class, () -> {
			Exceptions.wrap().run(() -> {
				throw new IOError(new IOException());
			});
		});
	}
	@Test public void wrap_checked() {
		WrappedException ex = assertThrows(WrappedException.class, () -> {
			Exceptions.wrap().run(() -> {
				throw new IOException();
			});
		});
		assertThat(ex.getCause(), instanceOf(IOException.class));
	}
	@Test public void wrap_interrupt() {
		WrappedException ex = assertThrows(WrappedException.class, () -> {
			Exceptions.wrap().run(() -> {
				throw new InterruptedException();
			});
		});
		assertThat(ex.getCause(), instanceOf(InterruptedException.class));
		assertTrue(Thread.interrupted());
	}
	@Test public void wrapIn_runtime() {
		assertThrows(NumberFormatException.class, () -> {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new NumberFormatException();
			});
		});
	}
	@Test public void wrapIn_error() {
		assertThrows(IOError.class, () -> {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new IOError(new IOException());
			});
		});
	}
	@Test public void wrapIn_checked() {
		CollectedException ex = assertThrows(CollectedException.class, () -> {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new IOException();
			});
		});
		assertThat(ex.getCause(), instanceOf(IOException.class));
	}
	@Test public void wrapIn_interrupt() {
		CollectedException ex = assertThrows(CollectedException.class, () -> {
			Exceptions.wrap(CollectedException::new).run(() -> {
				throw new InterruptedException();
			});
		});
		assertThat(ex.getCause(), instanceOf(InterruptedException.class));
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
