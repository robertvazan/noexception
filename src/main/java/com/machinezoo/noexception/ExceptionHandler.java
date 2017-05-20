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
		return new CatchingSupplier<T>(supplier);
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
	public final OptionalIntSupplier intSupplier(IntSupplier intSupplier) {
		return new CatchingIntSupplier(intSupplier);
	}
	@RequiredArgsConstructor private final class CatchingIntSupplier implements OptionalIntSupplier {
		private final IntSupplier intSupplier;
		@Override public OptionalInt get() {
			try {
				return OptionalInt.of(intSupplier.getAsInt());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongSupplier longSupplier(LongSupplier longSupplier) {
		return new CatchingLongSupplier(longSupplier);
	}
	@RequiredArgsConstructor private final class CatchingLongSupplier implements OptionalLongSupplier {
		private final LongSupplier longSupplier;
		@Override public OptionalLong get() {
			try {
				return OptionalLong.of(longSupplier.getAsLong());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleSupplier doubleSupplier(DoubleSupplier doubleSupplier) {
		return new CatchingDoubleSupplier(doubleSupplier);
	}
	@RequiredArgsConstructor private final class CatchingDoubleSupplier implements OptionalDoubleSupplier {
		private final DoubleSupplier doubleSupplier;
		@Override public OptionalDouble get() {
			try {
				return OptionalDouble.of(doubleSupplier.getAsDouble());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
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
