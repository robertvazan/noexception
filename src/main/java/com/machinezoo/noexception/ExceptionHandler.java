// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.optional.*;
import lombok.*;

public abstract class ExceptionHandler {
	public abstract boolean handle(Throwable exception);
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
	public final OptionalIntSupplier fromIntSupplier(IntSupplier supplier) {
		return new CatchingIntSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingIntSupplier implements OptionalIntSupplier {
		private final IntSupplier supplier;
		@Override public OptionalInt get() {
			try {
				return OptionalInt.of(supplier.getAsInt());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongSupplier fromLongSupplier(LongSupplier supplier) {
		return new CatchingLongSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingLongSupplier implements OptionalLongSupplier {
		private final LongSupplier supplier;
		@Override public OptionalLong get() {
			try {
				return OptionalLong.of(supplier.getAsLong());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleSupplier fromDoubleSupplier(DoubleSupplier supplier) {
		return new CatchingDoubleSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingDoubleSupplier implements OptionalDoubleSupplier {
		private final DoubleSupplier supplier;
		@Override public OptionalDouble get() {
			try {
				return OptionalDouble.of(supplier.getAsDouble());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalBooleanSupplier fromBooleanSupplier(BooleanSupplier supplier) {
		return new CatchingBooleanSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingBooleanSupplier implements OptionalBooleanSupplier {
		private final BooleanSupplier supplier;
		@Override public OptionalBoolean get() {
			try {
				return OptionalBoolean.of(supplier.getAsBoolean());
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> Consumer<T> consumer(Consumer<T> consumer) {
		return new CatchingConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CatchingConsumer<T> implements Consumer<T> {
		private final Consumer<T> consumer;
		@Override public void accept(T t) {
			try {
				consumer.accept(t);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final IntConsumer fromIntConsumer(IntConsumer consumer) {
		return new CatchingIntConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CatchingIntConsumer implements IntConsumer {
		private final IntConsumer consumer;
		@Override public void accept(int value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final LongConsumer fromLongConsumer(LongConsumer consumer) {
		return new CatchingLongConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CatchingLongConsumer implements LongConsumer {
		private final LongConsumer consumer;
		@Override public void accept(long value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final DoubleConsumer fromDoubleConsumer(DoubleConsumer consumer) {
		return new CatchingDoubleConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CatchingDoubleConsumer implements DoubleConsumer {
		private final DoubleConsumer consumer;
		@Override public void accept(double value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T, U> BiConsumer<T, U> fromBiConsumer(BiConsumer<T, U> consumer) {
		return new CatchingBiConsumer<T, U>(consumer);
	}
	@RequiredArgsConstructor private final class CatchingBiConsumer<T, U> implements BiConsumer<T, U> {
		private final BiConsumer<T, U> consumer;
		@Override public void accept(T t, U u) {
			try {
				consumer.accept(t, u);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T> ObjIntConsumer<T> fromObjIntConsumer(ObjIntConsumer<T> consumer) {
		return new CatchingObjIntConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CatchingObjIntConsumer<T> implements ObjIntConsumer<T> {
		private final ObjIntConsumer<T> consumer;
		@Override public void accept(T t, int value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T> ObjLongConsumer<T> fromObjLongConsumer(ObjLongConsumer<T> consumer) {
		return new CatchingObjLongConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CatchingObjLongConsumer<T> implements ObjLongConsumer<T> {
		private final ObjLongConsumer<T> consumer;
		@Override public void accept(T t, long value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ObjDoubleConsumer<T> consumer) {
		return new CatchingObjDoubleConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CatchingObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
		private final ObjDoubleConsumer<T> consumer;
		@Override public void accept(T t, double value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	public final <T, R> OptionalFunction<T, R> function(Function<T, R> function) {
		return new CatchingFunction<T, R>(function);
	}
	@RequiredArgsConstructor private final class CatchingFunction<T, R> implements OptionalFunction<T, R> {
		private final Function<T, R> function;
		@Override public Optional<R> apply(T t) {
			try {
				return Optional.ofNullable(function.apply(t));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalToIntFunction<T> fromToIntFunction(ToIntFunction<T> function) {
		return new CatchingToIntFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToIntFunction<T> implements OptionalToIntFunction<T> {
		private final ToIntFunction<T> function;
		@Override public OptionalInt apply(T t) {
			try {
				return OptionalInt.of(function.applyAsInt(t));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final <R> OptionalIntFunction<R> fromIntFunction(IntFunction<R> function) {
		return new CatchingIntFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingIntFunction<R> implements OptionalIntFunction<R> {
		private final IntFunction<R> function;
		@Override public Optional<R> apply(int value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalIntToLongFunction fromIntToLongFunction(IntToLongFunction function) {
		return new CatchingIntToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingIntToLongFunction implements OptionalIntToLongFunction {
		private final IntToLongFunction function;
		@Override public OptionalLong apply(int value) {
			try {
				return OptionalLong.of(function.applyAsLong(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalIntToDoubleFunction fromIntToDoubleFunction(IntToDoubleFunction function) {
		return new CatchingIntToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingIntToDoubleFunction implements OptionalIntToDoubleFunction {
		private final IntToDoubleFunction function;
		@Override public OptionalDouble apply(int value) {
			try {
				return OptionalDouble.of(function.applyAsDouble(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalToLongFunction<T> fromToLongFunction(ToLongFunction<T> function) {
		return new CatchingToLongFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToLongFunction<T> implements OptionalToLongFunction<T> {
		private final ToLongFunction<T> function;
		@Override public OptionalLong apply(T t) {
			try {
				return OptionalLong.of(function.applyAsLong(t));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final <R> OptionalLongFunction<R> fromLongFunction(LongFunction<R> function) {
		return new CatchingLongFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingLongFunction<R> implements OptionalLongFunction<R> {
		private final LongFunction<R> function;
		@Override public Optional<R> apply(long value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongToIntFunction fromLongToIntFunction(LongToIntFunction function) {
		return new CatchingLongToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingLongToIntFunction implements OptionalLongToIntFunction {
		private final LongToIntFunction function;
		@Override public OptionalInt apply(long value) {
			try {
				return OptionalInt.of(function.applyAsInt(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongToDoubleFunction fromLongToDoubleFunction(LongToDoubleFunction function) {
		return new CatchingLongToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingLongToDoubleFunction implements OptionalLongToDoubleFunction {
		private final LongToDoubleFunction function;
		@Override public OptionalDouble apply(long value) {
			try {
				return OptionalDouble.of(function.applyAsDouble(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalToDoubleFunction<T> fromToDoubleFunction(ToDoubleFunction<T> function) {
		return new CatchingToDoubleFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToDoubleFunction<T> implements OptionalToDoubleFunction<T> {
		private final ToDoubleFunction<T> function;
		@Override public OptionalDouble apply(T t) {
			try {
				return OptionalDouble.of(function.applyAsDouble(t));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <R> OptionalDoubleFunction<R> fromDoubleFunction(DoubleFunction<R> function) {
		return new CatchingDoubleFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleFunction<R> implements OptionalDoubleFunction<R> {
		private final DoubleFunction<R> function;
		@Override public Optional<R> apply(double value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleToIntFunction fromDoubleToIntFunction(DoubleToIntFunction function) {
		return new CatchingDoubleToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleToIntFunction implements OptionalDoubleToIntFunction {
		private final DoubleToIntFunction function;
		@Override public OptionalInt apply(double value) {
			try {
				return OptionalInt.of(function.applyAsInt(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleToLongFunction fromDoubleToLongFunction(DoubleToLongFunction function) {
		return new CatchingDoubleToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleToLongFunction implements OptionalDoubleToLongFunction {
		private final DoubleToLongFunction function;
		@Override public OptionalLong apply(double value) {
			try {
				return OptionalLong.of(function.applyAsLong(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalUnaryOperator<T> fromUnaryOperator(UnaryOperator<T> operator) {
		return new CatchingUnaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CatchingUnaryOperator<T> implements OptionalUnaryOperator<T> {
		private final UnaryOperator<T> operator;
		@Override public Optional<T> apply(T operand) {
			try {
				return Optional.ofNullable(operator.apply(operand));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalIntUnaryOperator fromIntUnaryOperator(IntUnaryOperator operator) {
		return new CatchingIntUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingIntUnaryOperator implements OptionalIntUnaryOperator {
		private final IntUnaryOperator operator;
		@Override public OptionalInt apply(int operand) {
			try {
				return OptionalInt.of(operator.applyAsInt(operand));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongUnaryOperator fromLongUnaryOperator(LongUnaryOperator operator) {
		return new CatchingLongUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingLongUnaryOperator implements OptionalLongUnaryOperator {
		private final LongUnaryOperator operator;
		@Override public OptionalLong apply(long operand) {
			try {
				return OptionalLong.of(operator.applyAsLong(operand));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleUnaryOperator fromDoubleUnaryOperator(DoubleUnaryOperator operator) {
		return new CatchingDoubleUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingDoubleUnaryOperator implements OptionalDoubleUnaryOperator {
		private final DoubleUnaryOperator operator;
		@Override public OptionalDouble apply(double operand) {
			try {
				return OptionalDouble.of(operator.applyAsDouble(operand));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <T, U, R> OptionalBiFunction<T, U, R> fromBiFunction(BiFunction<T, U, R> function) {
		return new CatchingBiFunction<T, U, R>(function);
	}
	@RequiredArgsConstructor private final class CatchingBiFunction<T, U, R> implements OptionalBiFunction<T, U, R> {
		private final BiFunction<T, U, R> function;
		@Override public Optional<R> apply(T t, U u) {
			try {
				return Optional.ofNullable(function.apply(t, u));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final <T, U> OptionalToIntBiFunction<T, U> fromToIntBiFunction(ToIntBiFunction<T, U> function) {
		return new CatchingToIntBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToIntBiFunction<T, U> implements OptionalToIntBiFunction<T, U> {
		private final ToIntBiFunction<T, U> function;
		@Override public OptionalInt apply(T t, U u) {
			try {
				return OptionalInt.of(function.applyAsInt(t, u));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final <T, U> OptionalToLongBiFunction<T, U> fromToLongBiFunction(ToLongBiFunction<T, U> function) {
		return new CatchingToLongBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToLongBiFunction<T, U> implements OptionalToLongBiFunction<T, U> {
		private final ToLongBiFunction<T, U> function;
		@Override public OptionalLong apply(T t, U u) {
			try {
				return OptionalLong.of(function.applyAsLong(t, u));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final <T, U> OptionalToDoubleBiFunction<T, U> fromToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
		return new CatchingToDoubleBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToDoubleBiFunction<T, U> implements OptionalToDoubleBiFunction<T, U> {
		private final ToDoubleBiFunction<T, U> function;
		@Override public OptionalDouble apply(T t, U u) {
			try {
				return OptionalDouble.of(function.applyAsDouble(t, u));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalBinaryOperator<T> fromBinaryOperator(BinaryOperator<T> operator) {
		return new CatchingBinaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CatchingBinaryOperator<T> implements OptionalBinaryOperator<T> {
		private final BinaryOperator<T> operator;
		@Override public Optional<T> apply(T left, T right) {
			try {
				return Optional.ofNullable(operator.apply(left, right));
			} catch (Throwable exception) {
				if (handle(exception))
					return Optional.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalIntBinaryOperator fromIntBinaryOperator(IntBinaryOperator operator) {
		return new CatchingIntBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingIntBinaryOperator implements OptionalIntBinaryOperator {
		private final IntBinaryOperator operator;
		@Override public OptionalInt apply(int left, int right) {
			try {
				return OptionalInt.of(operator.applyAsInt(left, right));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalInt.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongBinaryOperator fromLongBinaryOperator(LongBinaryOperator operator) {
		return new CatchingLongBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingLongBinaryOperator implements OptionalLongBinaryOperator {
		private final LongBinaryOperator operator;
		@Override public OptionalLong apply(long left, long right) {
			try {
				return OptionalLong.of(operator.applyAsLong(left, right));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalLong.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoubleBinaryOperator fromDoubleBinaryOperator(DoubleBinaryOperator operator) {
		return new CatchingDoubleBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingDoubleBinaryOperator implements OptionalDoubleBinaryOperator {
		private final DoubleBinaryOperator operator;
		@Override public OptionalDouble apply(double left, double right) {
			try {
				return OptionalDouble.of(operator.applyAsDouble(left, right));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalDouble.empty();
				else
					throw exception;
			}
		}
	}
	public final <T> OptionalPredicate<T> predicate(Predicate<T> predicate) {
		return new CatchingPredicate<T>(predicate);
	}
	@RequiredArgsConstructor private final class CatchingPredicate<T> implements OptionalPredicate<T> {
		private final Predicate<T> predicate;
		@Override public OptionalBoolean test(T t) {
			try {
				return OptionalBoolean.of(predicate.test(t));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalIntPredicate fromIntPredicate(IntPredicate predicate) {
		return new CatchingIntPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingIntPredicate implements OptionalIntPredicate {
		private final IntPredicate predicate;
		@Override public OptionalBoolean test(int value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalLongPredicate fromLongPredicate(LongPredicate predicate) {
		return new CatchingLongPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingLongPredicate implements OptionalLongPredicate {
		private final LongPredicate predicate;
		@Override public OptionalBoolean test(long value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
				else
					throw exception;
			}
		}
	}
	public final OptionalDoublePredicate fromDoublePredicate(DoublePredicate predicate) {
		return new CatchingDoublePredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingDoublePredicate implements OptionalDoublePredicate {
		private final DoublePredicate predicate;
		@Override public OptionalBoolean test(double value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
				else
					throw exception;
			}
		}
	}
	public final <T, U> OptionalBiPredicate<T, U> fromBiPredicate(BiPredicate<T, U> predicate) {
		return new CatchingBiPredicate<T, U>(predicate);
	}
	@RequiredArgsConstructor private final class CatchingBiPredicate<T, U> implements OptionalBiPredicate<T, U> {
		private final BiPredicate<T, U> predicate;
		@Override public OptionalBoolean test(T t, U u) {
			try {
				return OptionalBoolean.of(predicate.test(t, u));
			} catch (Throwable exception) {
				if (handle(exception))
					return OptionalBoolean.empty();
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
	public final OptionalInt getAsInt(IntSupplier supplier) {
		try {
			return OptionalInt.of(supplier.getAsInt());
		} catch (Throwable exception) {
			if (handle(exception))
				return OptionalInt.empty();
			else
				throw exception;
		}
	}
	public final OptionalLong getAsLong(LongSupplier supplier) {
		try {
			return OptionalLong.of(supplier.getAsLong());
		} catch (Throwable exception) {
			if (handle(exception))
				return OptionalLong.empty();
			else
				throw exception;
		}
	}
	public final OptionalDouble getAsDouble(DoubleSupplier supplier) {
		try {
			return OptionalDouble.of(supplier.getAsDouble());
		} catch (Throwable exception) {
			if (handle(exception))
				return OptionalDouble.empty();
			else
				throw exception;
		}
	}
	public final OptionalBoolean getAsBoolean(BooleanSupplier supplier) {
		try {
			return OptionalBoolean.of(supplier.getAsBoolean());
		} catch (Throwable exception) {
			if (handle(exception))
				return OptionalBoolean.empty();
			else
				throw exception;
		}
	}
}
