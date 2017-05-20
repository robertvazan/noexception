package com.machinezoo.noexception;

import java.util.function.*;
import com.machinezoo.noexception.throwing.*;
import lombok.*;

public abstract class CheckedExceptionHandler {
	public abstract void handle(Throwable exception);
	public final Runnable runnable(ThrowingRunnable runnable) {
		return new CheckedRunnable(runnable);
	}
	@RequiredArgsConstructor private final class CheckedRunnable implements Runnable {
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
		return new CheckedSupplier<T>(supplier);
	}
	@RequiredArgsConstructor private final class CheckedSupplier<T> implements Supplier<T> {
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
	public final IntSupplier fromIntSupplier(ThrowingIntSupplier supplier) {
		return new CheckedIntSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedIntSupplier implements IntSupplier {
		private final ThrowingIntSupplier supplier;
		@Override public int getAsInt() {
			try {
				return supplier.getAsInt();
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final LongSupplier fromLongSupplier(ThrowingLongSupplier supplier) {
		return new CheckedLongSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedLongSupplier implements LongSupplier {
		private final ThrowingLongSupplier supplier;
		@Override public long getAsLong() {
			try {
				return supplier.getAsLong();
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final DoubleSupplier fromDoubleSupplier(ThrowingDoubleSupplier supplier) {
		return new CheckedDoubleSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedDoubleSupplier implements DoubleSupplier {
		private final ThrowingDoubleSupplier supplier;
		@Override public double getAsDouble() {
			try {
				return supplier.getAsDouble();
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final BooleanSupplier fromBooleanSupplier(ThrowingBooleanSupplier supplier) {
		return new CheckedBooleanSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedBooleanSupplier implements BooleanSupplier {
		private final ThrowingBooleanSupplier supplier;
		@Override public boolean getAsBoolean() {
			try {
				return supplier.getAsBoolean();
			} catch (Throwable exception) {
				handle(exception);
				return false;
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
