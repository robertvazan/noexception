package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import org.slf4j.*;
import com.machinezoo.noexception.optional.*;
import lombok.*;

public abstract class ExceptionHandler {
	private static final ExceptionPassThrough pass = new ExceptionPassThrough();
	private static final ExceptionLogger log = new ExceptionLogger();
	private static final ExceptionSilencer silence = new ExceptionSilencer();
	public abstract boolean handle(Throwable exception);
	public static ExceptionHandler pass() {
		return pass;
	}
	public static ExceptionHandler log() {
		return log;
	}
	public static ExceptionHandler log(Logger logger) {
		return new CustomExceptionLogger(logger, "Caught exception");
	}
	public static ExceptionHandler log(Logger logger, String message) {
		return new CustomExceptionLogger(logger, message);
	}
	public static ExceptionHandler silence() {
		return silence;
	}
	public static ExceptionHandler define(Predicate<Throwable> handler) {
		return new CustomExceptionHandler(handler);
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
