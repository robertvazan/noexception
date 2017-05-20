package com.machinezoo.noexception;

import java.util.function.*;
import com.machinezoo.noexception.throwing.*;
import lombok.*;

public abstract class CheckedExceptionHandler {
	public abstract void handle(Throwable exception);
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
