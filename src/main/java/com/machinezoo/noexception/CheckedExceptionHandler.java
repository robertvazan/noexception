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
	public final <T> Consumer<T> consumer(ThrowingConsumer<T> consumer) {
		return new CheckedConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedConsumer<T> implements Consumer<T> {
		private final ThrowingConsumer<T> consumer;
		@Override public void accept(T t) {
			try {
				consumer.accept(t);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final IntConsumer fromIntConsumer(ThrowingIntConsumer consumer) {
		return new CheckedIntConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedIntConsumer implements IntConsumer {
		private final ThrowingIntConsumer consumer;
		@Override public void accept(int value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final LongConsumer fromLongConsumer(ThrowingLongConsumer consumer) {
		return new CheckedLongConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedLongConsumer implements LongConsumer {
		private final ThrowingLongConsumer consumer;
		@Override public void accept(long value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final DoubleConsumer fromDoubleConsumer(ThrowingDoubleConsumer consumer) {
		return new CheckedDoubleConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedDoubleConsumer implements DoubleConsumer {
		private final ThrowingDoubleConsumer consumer;
		@Override public void accept(double value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T, U> BiConsumer<T, U> fromBiConsumer(ThrowingBiConsumer<T, U> consumer) {
		return new CheckedBiConsumer<T, U>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedBiConsumer<T, U> implements BiConsumer<T, U> {
		private final ThrowingBiConsumer<T, U> consumer;
		@Override public void accept(T t, U u) {
			try {
				consumer.accept(t, u);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T> ObjIntConsumer<T> fromObjIntConsumer(ThrowingObjIntConsumer<T> consumer) {
		return new CheckedObjIntConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjIntConsumer<T> implements ObjIntConsumer<T> {
		private final ThrowingObjIntConsumer<T> consumer;
		@Override public void accept(T t, int value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T> ObjLongConsumer<T> fromObjLongConsumer(ThrowingObjLongConsumer<T> consumer) {
		return new CheckedObjLongConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjLongConsumer<T> implements ObjLongConsumer<T> {
		private final ThrowingObjLongConsumer<T> consumer;
		@Override public void accept(T t, long value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ThrowingObjDoubleConsumer<T> consumer) {
		return new CheckedObjDoubleConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
		private final ThrowingObjDoubleConsumer<T> consumer;
		@Override public void accept(T t, double value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
			}
		}
	}
	public final <T> Predicate<T> predicate(ThrowingPredicate<T> predicate) {
		return new CheckedPredicate<T>(predicate);
	}
	@RequiredArgsConstructor private final class CheckedPredicate<T> implements Predicate<T> {
		private final ThrowingPredicate<T> predicate;
		@Override public boolean test(T t) {
			try {
				return predicate.test(t);
			} catch (Throwable exception) {
				handle(exception);
				return false;
			}
		}
	}
	public final IntPredicate fromIntPredicate(ThrowingIntPredicate predicate) {
		return new CheckedIntPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedIntPredicate implements IntPredicate {
		private final ThrowingIntPredicate predicate;
		@Override public boolean test(int value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				return false;
			}
		}
	}
	public final LongPredicate fromLongPredicate(ThrowingLongPredicate predicate) {
		return new CheckedLongPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedLongPredicate implements LongPredicate {
		private final ThrowingLongPredicate predicate;
		@Override public boolean test(long value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				return false;
			}
		}
	}
	public final DoublePredicate fromDoublePredicate(ThrowingDoublePredicate predicate) {
		return new CheckedDoublePredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedDoublePredicate implements DoublePredicate {
		private final ThrowingDoublePredicate predicate;
		@Override public boolean test(double value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				return false;
			}
		}
	}
	public final <T, R> Function<T, R> function(ThrowingFunction<T, R> function) {
		return new CheckedFunction<T, R>(function);
	}
	@RequiredArgsConstructor private final class CheckedFunction<T, R> implements Function<T, R> {
		private final ThrowingFunction<T, R> function;
		@Override public R apply(T t) {
			try {
				return function.apply(t);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final <T> ToIntFunction<T> fromToIntFunction(ThrowingToIntFunction<T> function) {
		return new CheckedToIntFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToIntFunction<T> implements ToIntFunction<T> {
		private final ThrowingToIntFunction<T> function;
		@Override public int applyAsInt(T t) {
			try {
				return function.applyAsInt(t);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <R> IntFunction<R> fromIntFunction(ThrowingIntFunction<R> function) {
		return new CheckedIntFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedIntFunction<R> implements IntFunction<R> {
		private final ThrowingIntFunction<R> function;
		@Override public R apply(int value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final IntToLongFunction fromIntToLongFunction(ThrowingIntToLongFunction function) {
		return new CheckedIntToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedIntToLongFunction implements IntToLongFunction {
		private final ThrowingIntToLongFunction function;
		@Override public long applyAsLong(int value) {
			try {
				return function.applyAsLong(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final IntToDoubleFunction fromIntToDoubleFunction(ThrowingIntToDoubleFunction function) {
		return new CheckedIntToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedIntToDoubleFunction implements IntToDoubleFunction {
		private final ThrowingIntToDoubleFunction function;
		@Override public double applyAsDouble(int value) {
			try {
				return function.applyAsDouble(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T> ToLongFunction<T> fromToLongFunction(ThrowingToLongFunction<T> function) {
		return new CheckedToLongFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToLongFunction<T> implements ToLongFunction<T> {
		private final ThrowingToLongFunction<T> function;
		@Override public long applyAsLong(T t) {
			try {
				return function.applyAsLong(t);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <R> LongFunction<R> fromLongFunction(ThrowingLongFunction<R> function) {
		return new CheckedLongFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedLongFunction<R> implements LongFunction<R> {
		private final ThrowingLongFunction<R> function;
		@Override public R apply(long value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final LongToIntFunction fromLongToIntFunction(ThrowingLongToIntFunction function) {
		return new CheckedLongToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedLongToIntFunction implements LongToIntFunction {
		private final ThrowingLongToIntFunction function;
		@Override public int applyAsInt(long value) {
			try {
				return function.applyAsInt(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final LongToDoubleFunction fromLongToDoubleFunction(ThrowingLongToDoubleFunction function) {
		return new CheckedLongToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedLongToDoubleFunction implements LongToDoubleFunction {
		private final ThrowingLongToDoubleFunction function;
		@Override public double applyAsDouble(long value) {
			try {
				return function.applyAsDouble(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T> ToDoubleFunction<T> fromToDoubleFunction(ThrowingToDoubleFunction<T> function) {
		return new CheckedToDoubleFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToDoubleFunction<T> implements ToDoubleFunction<T> {
		private final ThrowingToDoubleFunction<T> function;
		@Override public double applyAsDouble(T t) {
			try {
				return function.applyAsDouble(t);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <R> DoubleFunction<R> fromDoubleFunction(ThrowingDoubleFunction<R> function) {
		return new CheckedDoubleFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleFunction<R> implements DoubleFunction<R> {
		private final ThrowingDoubleFunction<R> function;
		@Override public R apply(double value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final DoubleToIntFunction fromDoubleToIntFunction(ThrowingDoubleToIntFunction function) {
		return new CheckedDoubleToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleToIntFunction implements DoubleToIntFunction {
		private final ThrowingDoubleToIntFunction function;
		@Override public int applyAsInt(double value) {
			try {
				return function.applyAsInt(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final DoubleToLongFunction fromDoubleToLongFunction(ThrowingDoubleToLongFunction function) {
		return new CheckedDoubleToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleToLongFunction implements DoubleToLongFunction {
		private final ThrowingDoubleToLongFunction function;
		@Override public long applyAsLong(double value) {
			try {
				return function.applyAsLong(value);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T> UnaryOperator<T> fromUnaryOperator(ThrowingUnaryOperator<T> operator) {
		return new CheckedUnaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CheckedUnaryOperator<T> implements UnaryOperator<T> {
		private final ThrowingUnaryOperator<T> operator;
		@Override public T apply(T operand) {
			try {
				return operator.apply(operand);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final IntUnaryOperator fromIntUnaryOperator(ThrowingIntUnaryOperator operator) {
		return new CheckedIntUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedIntUnaryOperator implements IntUnaryOperator {
		private final ThrowingIntUnaryOperator operator;
		@Override public int applyAsInt(int operand) {
			try {
				return operator.applyAsInt(operand);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final LongUnaryOperator fromLongUnaryOperator(ThrowingLongUnaryOperator operator) {
		return new CheckedLongUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedLongUnaryOperator implements LongUnaryOperator {
		private final ThrowingLongUnaryOperator operator;
		@Override public long applyAsLong(long operand) {
			try {
				return operator.applyAsLong(operand);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final DoubleUnaryOperator fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator operator) {
		return new CheckedDoubleUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedDoubleUnaryOperator implements DoubleUnaryOperator {
		private final ThrowingDoubleUnaryOperator operator;
		@Override public double applyAsDouble(double operand) {
			try {
				return operator.applyAsDouble(operand);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T, U, R> BiFunction<T, U, R> fromBiFunction(ThrowingBiFunction<T, U, R> function) {
		return new CheckedBiFunction<T, U, R>(function);
	}
	@RequiredArgsConstructor private final class CheckedBiFunction<T, U, R> implements BiFunction<T, U, R> {
		private final ThrowingBiFunction<T, U, R> function;
		@Override public R apply(T t, U u) {
			try {
				return function.apply(t, u);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final <T, U> ToIntBiFunction<T, U> fromToIntBiFunction(ThrowingToIntBiFunction<T, U> function) {
		return new CheckedToIntBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
		private final ThrowingToIntBiFunction<T, U> function;
		@Override public int applyAsInt(T t, U u) {
			try {
				return function.applyAsInt(t, u);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T, U> ToLongBiFunction<T, U> fromToLongBiFunction(ThrowingToLongBiFunction<T, U> function) {
		return new CheckedToLongBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
		private final ThrowingToLongBiFunction<T, U> function;
		@Override public long applyAsLong(T t, U u) {
			try {
				return function.applyAsLong(t, u);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T, U> ToDoubleBiFunction<T, U> fromToDoubleBiFunction(ThrowingToDoubleBiFunction<T, U> function) {
		return new CheckedToDoubleBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
		private final ThrowingToDoubleBiFunction<T, U> function;
		@Override public double applyAsDouble(T t, U u) {
			try {
				return function.applyAsDouble(t, u);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final <T> BinaryOperator<T> fromBinaryOperator(ThrowingBinaryOperator<T> operator) {
		return new CheckedBinaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CheckedBinaryOperator<T> implements BinaryOperator<T> {
		private final ThrowingBinaryOperator<T> operator;
		@Override public T apply(T left, T right) {
			try {
				return operator.apply(left, right);
			} catch (Throwable exception) {
				handle(exception);
				return null;
			}
		}
	}
	public final IntBinaryOperator fromIntBinaryOperator(ThrowingIntBinaryOperator operator) {
		return new CheckedIntBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedIntBinaryOperator implements IntBinaryOperator {
		private final ThrowingIntBinaryOperator operator;
		@Override public int applyAsInt(int left, int right) {
			try {
				return operator.applyAsInt(left, right);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final LongBinaryOperator fromLongBinaryOperator(ThrowingLongBinaryOperator operator) {
		return new CheckedLongBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedLongBinaryOperator implements LongBinaryOperator {
		private final ThrowingLongBinaryOperator operator;
		@Override public long applyAsLong(long left, long right) {
			try {
				return operator.applyAsLong(left, right);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final DoubleBinaryOperator fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator operator) {
		return new CheckedDoubleBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedDoubleBinaryOperator implements DoubleBinaryOperator {
		private final ThrowingDoubleBinaryOperator operator;
		@Override public double applyAsDouble(double left, double right) {
			try {
				return operator.applyAsDouble(left, right);
			} catch (Throwable exception) {
				handle(exception);
				return 0;
			}
		}
	}
	public final void run(ThrowingRunnable runnable) {
		try {
			runnable.run();
		} catch (Throwable exception) {
			handle(exception);
		}
	}
	public final <T> T get(ThrowingSupplier<T> supplier) {
		try {
			return supplier.get();
		} catch (Throwable exception) {
			handle(exception);
			return null;
		}
	}
	public final int getAsInt(ThrowingIntSupplier supplier) {
		try {
			return supplier.getAsInt();
		} catch (Throwable exception) {
			handle(exception);
			return 0;
		}
	}
	public final long getAsLong(ThrowingLongSupplier supplier) {
		try {
			return supplier.getAsLong();
		} catch (Throwable exception) {
			handle(exception);
			return 0;
		}
	}
	public final double getAsDouble(ThrowingDoubleSupplier supplier) {
		try {
			return supplier.getAsDouble();
		} catch (Throwable exception) {
			handle(exception);
			return 0;
		}
	}
	public final boolean getAsBoolean(ThrowingBooleanSupplier supplier) {
		try {
			return supplier.getAsBoolean();
		} catch (Throwable exception) {
			handle(exception);
			return false;
		}
	}
}
