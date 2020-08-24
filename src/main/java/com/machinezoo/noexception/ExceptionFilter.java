// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;

/**
 * Represents exception handling policy that always ends with throwing another exception.
 * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
 * Method {@link #handle(Throwable)} defines the exception handling policy when implemented in derived class.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * <p>
 * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda)}
 * <p>
 * All wrapping methods surround the functional interface with a try-catch block.
 * If the functional interface throws, the exception is caught and passed to {@link #handle(Throwable)}.
 * {@link NullPointerException} from {@code null} functional interface is caught too.
 * Method {@link #handle(Throwable)} applies exception handling policy (log, count, ignore, etc.) and
 * throws a replacement or wrapping exception.
 * If it returns without throwing any exception, the original exception is rethrown.
 * <p>
 * Wrapping methods for all standard functional interfaces are provided.
 * Simple interfaces have short method names like {@link #runnable(Runnable)} or {@link #supplier(Supplier)}.
 * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(UnaryOperator)}.
 * Parameterless functional interfaces can be called directly by methods {@link #run(Runnable)}, {@link #get(Supplier)},
 * and the various {@code getAsX} variants.
 * 
 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
 * @see #handle(Throwable)
 * @see ExceptionHandler#passing()
 * @see ExceptionHandler
 * @see CheckedExceptionHandler
 */
public abstract class ExceptionFilter {
	/**
	 * Handles exception in a generic way. This method must be defined in a derived class.
	 * One built-in implementation is provided by {@link ExceptionHandler#passing()}.
	 * All other methods of the {@code ExceptionFilter} call this method, but it can be also called directly.
	 * <p>
	 * This method represents reusable catch block that handles all exceptions in the same way.
	 * When invoked, it must somehow handle the provided exception, for example by logging it.
	 * It can also replace or wrap the exception by throwing a new exception.
	 * If this method returns without throwing, it is a signal that the original exception should be rethrown.
	 * All other methods of this class will rethrow in that case.
	 * 
	 * @param exception
	 *            the exception to handle
	 * @throws NullPointerException
	 *             if {@code exception} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public abstract void handle(Throwable exception);
	/**
	 * Initializes new {@code ExceptionFilter}.
	 */
	protected ExceptionFilter() {
	}
	/**
	 * Applies exception filter to {@link Runnable}.
	 * <p>
	 * If {@code runnable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code runnable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingRunnable(Exceptions.log().passing().runnable(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@link Runnable} to wrap, usually a lambda
	 * @return wrapper that runs {@link Runnable} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final Runnable runnable(Runnable runnable) {
		return new FilteredRunnable(runnable);
	}
	private final class FilteredRunnable implements Runnable {
		private final Runnable runnable;
		FilteredRunnable(Runnable runnable) {
			this.runnable = runnable;
		}
		@Override
		public void run() {
			try {
				runnable.run();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link Supplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingSupplier(Exceptions.log().passing().supplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link Supplier} to wrap, usually a lambda
	 * @return wrapper that runs {@link Supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Supplier<T> supplier(Supplier<T> supplier) {
		return new FilteredSupplier<T>(supplier);
	}
	private final class FilteredSupplier<T> implements Supplier<T> {
		private final Supplier<T> supplier;
		FilteredSupplier(Supplier<T> supplier) {
			this.supplier = supplier;
		}
		@Override
		public T get() {
			try {
				return supplier.get();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntSupplier(Exceptions.log().passing().fromIntSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link IntSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntSupplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntSupplier fromIntSupplier(IntSupplier supplier) {
		return new FilteredIntSupplier(supplier);
	}
	private final class FilteredIntSupplier implements IntSupplier {
		private final IntSupplier supplier;
		FilteredIntSupplier(IntSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public int getAsInt() {
			try {
				return supplier.getAsInt();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongSupplier(Exceptions.log().passing().fromLongSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link LongSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongSupplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongSupplier fromLongSupplier(LongSupplier supplier) {
		return new FilteredLongSupplier(supplier);
	}
	private final class FilteredLongSupplier implements LongSupplier {
		private final LongSupplier supplier;
		FilteredLongSupplier(LongSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public long getAsLong() {
			try {
				return supplier.getAsLong();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleSupplier(Exceptions.log().passing().fromDoubleSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link DoubleSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleSupplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleSupplier fromDoubleSupplier(DoubleSupplier supplier) {
		return new FilteredDoubleSupplier(supplier);
	}
	private final class FilteredDoubleSupplier implements DoubleSupplier {
		private final DoubleSupplier supplier;
		FilteredDoubleSupplier(DoubleSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public double getAsDouble() {
			try {
				return supplier.getAsDouble();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBooleanSupplier(Exceptions.log().passing().fromBooleanSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link BooleanSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@link BooleanSupplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final BooleanSupplier fromBooleanSupplier(BooleanSupplier supplier) {
		return new FilteredBooleanSupplier(supplier);
	}
	private final class FilteredBooleanSupplier implements BooleanSupplier {
		private final BooleanSupplier supplier;
		FilteredBooleanSupplier(BooleanSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public boolean getAsBoolean() {
			try {
				return supplier.getAsBoolean();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link Consumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingConsumer(Exceptions.log().passing().consumer(t -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link Consumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link Consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Consumer<T> consumer(Consumer<T> consumer) {
		return new FilteredConsumer<T>(consumer);
	}
	private final class FilteredConsumer<T> implements Consumer<T> {
		private final Consumer<T> consumer;
		FilteredConsumer(Consumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t) {
			try {
				consumer.accept(t);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntConsumer(Exceptions.log().passing().fromIntConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link IntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntConsumer fromIntConsumer(IntConsumer consumer) {
		return new FilteredIntConsumer(consumer);
	}
	private final class FilteredIntConsumer implements IntConsumer {
		private final IntConsumer consumer;
		FilteredIntConsumer(IntConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(int value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongConsumer(Exceptions.log().passing().fromLongConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link LongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongConsumer fromLongConsumer(LongConsumer consumer) {
		return new FilteredLongConsumer(consumer);
	}
	private final class FilteredLongConsumer implements LongConsumer {
		private final LongConsumer consumer;
		FilteredLongConsumer(LongConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(long value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleConsumer(Exceptions.log().passing().fromDoubleConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link DoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleConsumer fromDoubleConsumer(DoubleConsumer consumer) {
		return new FilteredDoubleConsumer(consumer);
	}
	private final class FilteredDoubleConsumer implements DoubleConsumer {
		private final DoubleConsumer consumer;
		FilteredDoubleConsumer(DoubleConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(double value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link BiConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiConsumer(Exceptions.log().passing().fromBiConsumer((t, u) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link BiConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link BiConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> BiConsumer<T, U> fromBiConsumer(BiConsumer<T, U> consumer) {
		return new FilteredBiConsumer<T, U>(consumer);
	}
	private final class FilteredBiConsumer<T, U> implements BiConsumer<T, U> {
		private final BiConsumer<T, U> consumer;
		FilteredBiConsumer(BiConsumer<T, U> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, U u) {
			try {
				consumer.accept(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ObjIntConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjIntConsumer(Exceptions.log().passing().fromObjIntConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link ObjIntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link ObjIntConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjIntConsumer<T> fromObjIntConsumer(ObjIntConsumer<T> consumer) {
		return new FilteredObjIntConsumer<T>(consumer);
	}
	private final class FilteredObjIntConsumer<T> implements ObjIntConsumer<T> {
		private final ObjIntConsumer<T> consumer;
		FilteredObjIntConsumer(ObjIntConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, int value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ObjLongConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjLongConsumer(Exceptions.log().passing().fromObjLongConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link ObjLongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link ObjLongConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjLongConsumer<T> fromObjLongConsumer(ObjLongConsumer<T> consumer) {
		return new FilteredObjLongConsumer<T>(consumer);
	}
	private final class FilteredObjLongConsumer<T> implements ObjLongConsumer<T> {
		private final ObjLongConsumer<T> consumer;
		FilteredObjLongConsumer(ObjLongConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, long value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ObjDoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjDoubleConsumer(Exceptions.log().passing().fromObjDoubleConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@link ObjDoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@link ObjDoubleConsumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ObjDoubleConsumer<T> consumer) {
		return new FilteredObjDoubleConsumer<T>(consumer);
	}
	private final class FilteredObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
		private final ObjDoubleConsumer<T> consumer;
		FilteredObjDoubleConsumer(ObjDoubleConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, double value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link Predicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingPredicate(Exceptions.log().passing().predicate(t -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@link Predicate} to wrap, usually a lambda
	 * @return wrapper that runs {@link Predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Predicate<T> predicate(Predicate<T> predicate) {
		return new FilteredPredicate<T>(predicate);
	}
	private final class FilteredPredicate<T> implements Predicate<T> {
		private final Predicate<T> predicate;
		FilteredPredicate(Predicate<T> predicate) {
			this.predicate = predicate;
		}
		@Override
		public boolean test(T t) {
			try {
				return predicate.test(t);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntPredicate(Exceptions.log().passing().fromIntPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@link IntPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntPredicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntPredicate fromIntPredicate(IntPredicate predicate) {
		return new FilteredIntPredicate(predicate);
	}
	private final class FilteredIntPredicate implements IntPredicate {
		private final IntPredicate predicate;
		FilteredIntPredicate(IntPredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public boolean test(int value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongPredicate(Exceptions.log().passing().fromLongPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@link LongPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongPredicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongPredicate fromLongPredicate(LongPredicate predicate) {
		return new FilteredLongPredicate(predicate);
	}
	private final class FilteredLongPredicate implements LongPredicate {
		private final LongPredicate predicate;
		FilteredLongPredicate(LongPredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public boolean test(long value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoublePredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoublePredicate(Exceptions.log().passing().fromDoublePredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@link DoublePredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoublePredicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoublePredicate fromDoublePredicate(DoublePredicate predicate) {
		return new FilteredDoublePredicate(predicate);
	}
	private final class FilteredDoublePredicate implements DoublePredicate {
		private final DoublePredicate predicate;
		FilteredDoublePredicate(DoublePredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public boolean test(double value) {
			try {
				return predicate.test(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link BiPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiPredicate(Exceptions.log().passing().fromBiPredicate((t, u) -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@link BiPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@link BiPredicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> BiPredicate<T, U> fromBiPredicate(BiPredicate<T, U> predicate) {
		return new FilteredBiPredicate<T, U>(predicate);
	}
	private final class FilteredBiPredicate<T, U> implements BiPredicate<T, U> {
		private final BiPredicate<T, U> predicate;
		FilteredBiPredicate(BiPredicate<T, U> predicate) {
			this.predicate = predicate;
		}
		@Override
		public boolean test(T t, U u) {
			try {
				return predicate.test(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link Function}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingFunction(Exceptions.log().passing().function(t -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link Function} to wrap, usually a lambda
	 * @return wrapper that runs {@link Function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, R> Function<T, R> function(Function<T, R> function) {
		return new FilteredFunction<T, R>(function);
	}
	private final class FilteredFunction<T, R> implements Function<T, R> {
		private final Function<T, R> function;
		FilteredFunction(Function<T, R> function) {
			this.function = function;
		}
		@Override
		public R apply(T t) {
			try {
				return function.apply(t);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToIntFunction(Exceptions.log().passing().fromToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToIntFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToIntFunction<T> fromToIntFunction(ToIntFunction<T> function) {
		return new FilteredToIntFunction<T>(function);
	}
	private final class FilteredToIntFunction<T> implements ToIntFunction<T> {
		private final ToIntFunction<T> function;
		FilteredToIntFunction(ToIntFunction<T> function) {
			this.function = function;
		}
		@Override
		public int applyAsInt(T value) {
			try {
				return function.applyAsInt(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntFunction(Exceptions.log().passing().fromIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link IntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> IntFunction<R> fromIntFunction(IntFunction<R> function) {
		return new FilteredIntFunction<R>(function);
	}
	private final class FilteredIntFunction<R> implements IntFunction<R> {
		private final IntFunction<R> function;
		FilteredIntFunction(IntFunction<R> function) {
			this.function = function;
		}
		@Override
		public R apply(int value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntToLongFunction(Exceptions.log().passing().fromIntToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link IntToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntToLongFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntToLongFunction fromIntToLongFunction(IntToLongFunction function) {
		return new FilteredIntToLongFunction(function);
	}
	private final class FilteredIntToLongFunction implements IntToLongFunction {
		private final IntToLongFunction function;
		FilteredIntToLongFunction(IntToLongFunction function) {
			this.function = function;
		}
		@Override
		public long applyAsLong(int value) {
			try {
				return function.applyAsLong(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntToDoubleFunction(Exceptions.log().passing().fromIntToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link IntToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntToDoubleFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntToDoubleFunction fromIntToDoubleFunction(IntToDoubleFunction function) {
		return new FilteredIntToDoubleFunction(function);
	}
	private final class FilteredIntToDoubleFunction implements IntToDoubleFunction {
		private final IntToDoubleFunction function;
		FilteredIntToDoubleFunction(IntToDoubleFunction function) {
			this.function = function;
		}
		@Override
		public double applyAsDouble(int value) {
			try {
				return function.applyAsDouble(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToLongFunction(Exceptions.log().passing().fromToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToLongFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToLongFunction<T> fromToLongFunction(ToLongFunction<T> function) {
		return new FilteredToLongFunction<T>(function);
	}
	private final class FilteredToLongFunction<T> implements ToLongFunction<T> {
		private final ToLongFunction<T> function;
		FilteredToLongFunction(ToLongFunction<T> function) {
			this.function = function;
		}
		@Override
		public long applyAsLong(T value) {
			try {
				return function.applyAsLong(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongFunction(Exceptions.log().passing().fromLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link LongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> LongFunction<R> fromLongFunction(LongFunction<R> function) {
		return new FilteredLongFunction<R>(function);
	}
	private final class FilteredLongFunction<R> implements LongFunction<R> {
		private final LongFunction<R> function;
		FilteredLongFunction(LongFunction<R> function) {
			this.function = function;
		}
		@Override
		public R apply(long value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongToIntFunction(Exceptions.log().passing().fromLongToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link LongToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongToIntFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongToIntFunction fromLongToIntFunction(LongToIntFunction function) {
		return new FilteredLongToIntFunction(function);
	}
	private final class FilteredLongToIntFunction implements LongToIntFunction {
		private final LongToIntFunction function;
		FilteredLongToIntFunction(LongToIntFunction function) {
			this.function = function;
		}
		@Override
		public int applyAsInt(long value) {
			try {
				return function.applyAsInt(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongToDoubleFunction(Exceptions.log().passing().fromLongToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link LongToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongToDoubleFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongToDoubleFunction fromLongToDoubleFunction(LongToDoubleFunction function) {
		return new FilteredLongToDoubleFunction(function);
	}
	private final class FilteredLongToDoubleFunction implements LongToDoubleFunction {
		private final LongToDoubleFunction function;
		FilteredLongToDoubleFunction(LongToDoubleFunction function) {
			this.function = function;
		}
		@Override
		public double applyAsDouble(long value) {
			try {
				return function.applyAsDouble(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleFunction(Exceptions.log().passing().fromToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToDoubleFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToDoubleFunction<T> fromToDoubleFunction(ToDoubleFunction<T> function) {
		return new FilteredToDoubleFunction<T>(function);
	}
	private final class FilteredToDoubleFunction<T> implements ToDoubleFunction<T> {
		private final ToDoubleFunction<T> function;
		FilteredToDoubleFunction(ToDoubleFunction<T> function) {
			this.function = function;
		}
		@Override
		public double applyAsDouble(T value) {
			try {
				return function.applyAsDouble(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleFunction(Exceptions.log().passing().fromDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link DoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> DoubleFunction<R> fromDoubleFunction(DoubleFunction<R> function) {
		return new FilteredDoubleFunction<R>(function);
	}
	private final class FilteredDoubleFunction<R> implements DoubleFunction<R> {
		private final DoubleFunction<R> function;
		FilteredDoubleFunction(DoubleFunction<R> function) {
			this.function = function;
		}
		@Override
		public R apply(double value) {
			try {
				return function.apply(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToIntFunction(Exceptions.log().passing().fromDoubleToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link DoubleToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleToIntFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleToIntFunction fromDoubleToIntFunction(DoubleToIntFunction function) {
		return new FilteredDoubleToIntFunction(function);
	}
	private final class FilteredDoubleToIntFunction implements DoubleToIntFunction {
		private final DoubleToIntFunction function;
		FilteredDoubleToIntFunction(DoubleToIntFunction function) {
			this.function = function;
		}
		@Override
		public int applyAsInt(double value) {
			try {
				return function.applyAsInt(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToLongFunction(Exceptions.log().passing().fromDoubleToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link DoubleToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleToLongFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleToLongFunction fromDoubleToLongFunction(DoubleToLongFunction function) {
		return new FilteredDoubleToLongFunction(function);
	}
	private final class FilteredDoubleToLongFunction implements DoubleToLongFunction {
		private final DoubleToLongFunction function;
		FilteredDoubleToLongFunction(DoubleToLongFunction function) {
			this.function = function;
		}
		@Override
		public long applyAsLong(double value) {
			try {
				return function.applyAsLong(value);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link UnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingUnaryOperator(Exceptions.log().passing().fromUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link UnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link UnaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> UnaryOperator<T> fromUnaryOperator(UnaryOperator<T> operator) {
		return new FilteredUnaryOperator<T>(operator);
	}
	private final class FilteredUnaryOperator<T> implements UnaryOperator<T> {
		private final UnaryOperator<T> operator;
		FilteredUnaryOperator(UnaryOperator<T> operator) {
			this.operator = operator;
		}
		@Override
		public T apply(T operand) {
			try {
				return operator.apply(operand);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntUnaryOperator(Exceptions.log().passing().fromIntUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link IntUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntUnaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntUnaryOperator fromIntUnaryOperator(IntUnaryOperator operator) {
		return new FilteredIntUnaryOperator(operator);
	}
	private final class FilteredIntUnaryOperator implements IntUnaryOperator {
		private final IntUnaryOperator operator;
		FilteredIntUnaryOperator(IntUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public int applyAsInt(int operand) {
			try {
				return operator.applyAsInt(operand);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongUnaryOperator(Exceptions.log().passing().fromLongUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link LongUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongUnaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongUnaryOperator fromLongUnaryOperator(LongUnaryOperator operator) {
		return new FilteredLongUnaryOperator(operator);
	}
	private final class FilteredLongUnaryOperator implements LongUnaryOperator {
		private final LongUnaryOperator operator;
		FilteredLongUnaryOperator(LongUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public long applyAsLong(long operand) {
			try {
				return operator.applyAsLong(operand);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleUnaryOperator(Exceptions.log().passing().fromDoubleUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link DoubleUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleUnaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleUnaryOperator fromDoubleUnaryOperator(DoubleUnaryOperator operator) {
		return new FilteredDoubleUnaryOperator(operator);
	}
	private final class FilteredDoubleUnaryOperator implements DoubleUnaryOperator {
		private final DoubleUnaryOperator operator;
		FilteredDoubleUnaryOperator(DoubleUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public double applyAsDouble(double operand) {
			try {
				return operator.applyAsDouble(operand);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link BiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiFunction(Exceptions.log().passing().fromBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link BiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link BiFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U, R> BiFunction<T, U, R> fromBiFunction(BiFunction<T, U, R> function) {
		return new FilteredBiFunction<T, U, R>(function);
	}
	private final class FilteredBiFunction<T, U, R> implements BiFunction<T, U, R> {
		private final BiFunction<T, U, R> function;
		FilteredBiFunction(BiFunction<T, U, R> function) {
			this.function = function;
		}
		@Override
		public R apply(T t, U u) {
			try {
				return function.apply(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToIntBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToIntBiFunction(Exceptions.log().passing().fromToIntBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToIntBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToIntBiFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToIntBiFunction<T, U> fromToIntBiFunction(ToIntBiFunction<T, U> function) {
		return new FilteredToIntBiFunction<T, U>(function);
	}
	private final class FilteredToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
		private final ToIntBiFunction<T, U> function;
		FilteredToIntBiFunction(ToIntBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public int applyAsInt(T t, U u) {
			try {
				return function.applyAsInt(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToLongBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToLongBiFunction(Exceptions.log().passing().fromToLongBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToLongBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToLongBiFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToLongBiFunction<T, U> fromToLongBiFunction(ToLongBiFunction<T, U> function) {
		return new FilteredToLongBiFunction<T, U>(function);
	}
	private final class FilteredToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
		private final ToLongBiFunction<T, U> function;
		FilteredToLongBiFunction(ToLongBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public long applyAsLong(T t, U u) {
			try {
				return function.applyAsLong(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link ToDoubleBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleBiFunction(Exceptions.log().passing().fromToDoubleBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@link ToDoubleBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@link ToDoubleBiFunction} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToDoubleBiFunction<T, U> fromToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
		return new FilteredToDoubleBiFunction<T, U>(function);
	}
	private final class FilteredToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
		private final ToDoubleBiFunction<T, U> function;
		FilteredToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public double applyAsDouble(T t, U u) {
			try {
				return function.applyAsDouble(t, u);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link BinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBinaryOperator(Exceptions.log().passing().fromBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link BinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link BinaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> BinaryOperator<T> fromBinaryOperator(BinaryOperator<T> operator) {
		return new FilteredBinaryOperator<T>(operator);
	}
	private final class FilteredBinaryOperator<T> implements BinaryOperator<T> {
		private final BinaryOperator<T> operator;
		FilteredBinaryOperator(BinaryOperator<T> operator) {
			this.operator = operator;
		}
		@Override
		public T apply(T left, T right) {
			try {
				return operator.apply(left, right);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link IntBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntBinaryOperator(Exceptions.log().passing().fromIntBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link IntBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link IntBinaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntBinaryOperator fromIntBinaryOperator(IntBinaryOperator operator) {
		return new FilteredIntBinaryOperator(operator);
	}
	private final class FilteredIntBinaryOperator implements IntBinaryOperator {
		private final IntBinaryOperator operator;
		FilteredIntBinaryOperator(IntBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public int applyAsInt(int left, int right) {
			try {
				return operator.applyAsInt(left, right);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link LongBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongBinaryOperator(Exceptions.log().passing().fromLongBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link LongBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link LongBinaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongBinaryOperator fromLongBinaryOperator(LongBinaryOperator operator) {
		return new FilteredLongBinaryOperator(operator);
	}
	private final class FilteredLongBinaryOperator implements LongBinaryOperator {
		private final LongBinaryOperator operator;
		FilteredLongBinaryOperator(LongBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public long applyAsLong(long left, long right) {
			try {
				return operator.applyAsLong(left, right);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link DoubleBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleBinaryOperator(Exceptions.log().passing().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@link DoubleBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@link DoubleBinaryOperator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleBinaryOperator fromDoubleBinaryOperator(DoubleBinaryOperator operator) {
		return new FilteredDoubleBinaryOperator(operator);
	}
	private final class FilteredDoubleBinaryOperator implements DoubleBinaryOperator {
		private final DoubleBinaryOperator operator;
		FilteredDoubleBinaryOperator(DoubleBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public double applyAsDouble(double left, double right) {
			try {
				return operator.applyAsDouble(left, right);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link Comparator}.
	 * <p>
	 * If {@code comparator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code comparator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingComparator(Exceptions.log().passing().comparator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param comparator
	 *            the {@link Comparator} to wrap, usually a lambda
	 * @return wrapper that runs {@link Comparator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Comparator<T> comparator(Comparator<T> comparator) {
		return new FilteredComparator<T>(comparator);
	}
	private final class FilteredComparator<T> implements Comparator<T> {
		private final Comparator<T> comparator;
		FilteredComparator(Comparator<T> comparator) {
			this.comparator = comparator;
		}
		@Override
		public int compare(T left, T right) {
			try {
				return comparator.compare(left, right);
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Applies exception filter to {@link CloseableScope}.
	 * <p>
	 * If {@code closeable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code closeable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code try (var scope = Exceptions.log().passing().closeable(openSomething()))}
	 * 
	 * @param closeable
	 *            the {@link CloseableScope} to wrap
	 * @return wrapper that runs {@link CloseableScope} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final CloseableScope closeable(CloseableScope closeable) {
		return new FilteredCloseableScope(closeable);
	}
	private final class FilteredCloseableScope implements CloseableScope {
		private final CloseableScope closeable;
		FilteredCloseableScope(CloseableScope closeable) {
			this.closeable = closeable;
		}
		@Override
		public void close() {
			try {
				closeable.close();
			} catch (Throwable exception) {
				handle(exception);
				throw exception;
			}
		}
	}
	/**
	 * Filters exceptions while running {@link Runnable}.
	 * <p>
	 * If {@code runnable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code runnable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().run(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@link Runnable} to run, usually a lambda
	 * @throws NullPointerException
	 *             if {@code runnable} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final void run(Runnable runnable) {
		try {
			runnable.run();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
	/**
	 * Filters exceptions while running {@link Supplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link Supplier} to run, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> T get(Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
	/**
	 * Filters exceptions while running {@link IntSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsInt(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link IntSupplier} to run, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final int getAsInt(IntSupplier supplier) {
		try {
			return supplier.getAsInt();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
	/**
	 * Filters exceptions while running {@link LongSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsLong(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link LongSupplier} to run, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final long getAsLong(LongSupplier supplier) {
		try {
			return supplier.getAsLong();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
	/**
	 * Filters exceptions while running {@link DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsDouble(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link DoubleSupplier} to run, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final double getAsDouble(DoubleSupplier supplier) {
		try {
			return supplier.getAsDouble();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
	/**
	 * Filters exceptions while running {@link BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsBoolean(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@link BooleanSupplier} to run, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final boolean getAsBoolean(BooleanSupplier supplier) {
		try {
			return supplier.getAsBoolean();
		} catch (Throwable exception) {
			handle(exception);
			throw exception;
		}
	}
}
