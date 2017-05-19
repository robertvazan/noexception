package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import org.slf4j.*;
import com.machinezoo.noexception.optional.*;
import lombok.*;

public abstract class ExceptionHandler {
	public abstract boolean handle(Throwable exception);
	private static final ExceptionPassThrough pass = new ExceptionPassThrough();
	public static ExceptionHandler pass() {
		return pass;
	}
	private static final class ExceptionPassThrough extends ExceptionHandler {
		@Override public boolean handle(Throwable exception) {
			return false;
		}
	}
	private static final ExceptionLogger log = new ExceptionLogger();
	public static ExceptionHandler log() {
		return log;
	}
	private static final class ExceptionLogger extends ExceptionHandler {
		private static final Logger logger = LoggerFactory.getLogger(ExceptionLogger.class);
		@Override public boolean handle(Throwable exception) {
			logger.error("Caught exception", exception);
			return true;
		}
	}
	public static ExceptionHandler log(Logger logger) {
		return new CustomExceptionLogger(logger, "Caught exception");
	}
	public static ExceptionHandler log(Logger logger, String message) {
		return new CustomExceptionLogger(logger, message);
	}
	@RequiredArgsConstructor private static final class CustomExceptionLogger extends ExceptionHandler {
		private final Logger logger;
		private final String message;
		@Override public boolean handle(Throwable exception) {
			logger.error(message, exception);
			return true;
		}
	}
	private static final ExceptionSilencer silence = new ExceptionSilencer();
	public static ExceptionHandler silence() {
		return silence;
	}
	private static final class ExceptionSilencer extends ExceptionHandler {
		@Override public boolean handle(Throwable exception) {
			return true;
		}
	}
	public static ExceptionHandler define(Predicate<Throwable> handler) {
		return new CustomExceptionHandler(handler);
	}
	@RequiredArgsConstructor private static final class CustomExceptionHandler extends ExceptionHandler {
		private final Predicate<Throwable> handler;
		@Override public boolean handle(Throwable exception) {
			return handler.test(exception);
		}
	}
	public final Runnable runnable(Runnable runnable) {
		return new CatchingRunnable(runnable);
	}
	@RequiredArgsConstructor private final class CatchingRunnable implements Runnable {
		private final Runnable runnable;
		@Override public void run() {
			try {
				runnable.run();
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T> OptionalSupplier<T> supplier(Supplier<T> supplier) {
		return new CatchingSupplier<>(supplier);
	}
	@RequiredArgsConstructor private final class CatchingSupplier<T> implements OptionalSupplier<T> {
		private final Supplier<T> supplier;
		@Override public Optional<T> get() {
			try {
				return Optional.ofNullable(supplier.get());
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final void run(Runnable runnable) {
		try {
			runnable.run();
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
		}
	}
	public final <T> Optional<T> get(Supplier<T> supplier) {
		try {
			return Optional.ofNullable(supplier.get());
		} catch (Throwable exception) {
			if (handle(exception))
				return Optional.empty();
			else
				throw exception;
		}
	}
}
