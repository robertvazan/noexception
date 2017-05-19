package com.machinezoo.noexception;

import java.util.function.*;
import com.machinezoo.noexception.throwing.*;
import lombok.*;

public abstract class CheckedExceptions {
	public abstract void handle(Throwable exception);
	private static final SneakyExceptions sneak = new SneakyExceptions();
	public static CheckedExceptions sneak() {
		return sneak;
	}
	private static final class SneakyExceptions extends CheckedExceptions {
		@Override @SneakyThrows public void handle(Throwable exception) {
			throw exception;
		}
	}
	private static final WrappedExceptions wrap = new WrappedExceptions();
	public static CheckedExceptions wrap() {
		return wrap;
	}
	private static final class WrappedExceptions extends CheckedExceptions {
		@Override public void handle(Throwable exception) {
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			if (exception instanceof Error)
				throw (Error)exception;
			throw new WrappedException(exception);
		}
	}
	public static CheckedExceptions wrap(Function<Throwable, RuntimeException> wrapper) {
		return new CustomWrapperExceptions(wrapper);
	}
	@RequiredArgsConstructor private static final class CustomWrapperExceptions extends CheckedExceptions {
		private final Function<Throwable, RuntimeException> wrapper;
		@Override public void handle(Throwable exception) {
			throw wrapper.apply(exception);
		}
	}
	public final Runnable runnable(ThrowingRunnable runnable) {
		return new SneakyRunnable(runnable);
	}
	@RequiredArgsConstructor private final class SneakyRunnable implements Runnable {
		private final ThrowingRunnable runnable;
		@Override public void run() {
			try {
				runnable.run();
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T> Supplier<T> supplier(ThrowingSupplier<T> supplier) {
		return new SneakySupplier<>(supplier);
	}
	@RequiredArgsConstructor private final class SneakySupplier<T> implements Supplier<T> {
		private final ThrowingSupplier<T> supplier;
		@Override public T get() {
			try {
				return supplier.get();
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final void run(Runnable runnable) {
		try {
			runnable.run();
		} catch (Throwable exception) {
			handle(exception);
		}
	}
	public final <T> T get(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (Throwable exception) {
			handle(exception);
			return null;
		}
	}
}
