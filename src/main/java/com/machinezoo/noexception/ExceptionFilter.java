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
 * {@code NullPointerException} from null functional interface is caught too.
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
	 * Applies exception filter to {@code Runnable}.
	 * <p>
	 * If {@code runnable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code runnable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingRunnable(Exceptions.log().passing().runnable(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@code Runnable} to wrap, usually a lambda
	 * @return wrapper that runs {@code Runnable} in a try-catch block
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
	 * Applies exception filter to {@code Supplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingSupplier(Exceptions.log().passing().supplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code Supplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code Supplier} in a try-catch block
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
	 * Applies exception filter to {@code IntSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntSupplier(Exceptions.log().passing().fromIntSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code IntSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntSupplier} in a try-catch block
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
	 * Applies exception filter to {@code LongSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongSupplier(Exceptions.log().passing().fromLongSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code LongSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongSupplier} in a try-catch block
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
	 * Applies exception filter to {@code DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleSupplier(Exceptions.log().passing().fromDoubleSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code DoubleSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleSupplier} in a try-catch block
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
	 * Applies exception filter to {@code BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBooleanSupplier(Exceptions.log().passing().fromBooleanSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code BooleanSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code BooleanSupplier} in a try-catch block
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
	 * Applies exception filter to {@code Consumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingConsumer(Exceptions.log().passing().consumer(t -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code Consumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code Consumer} in a try-catch block
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
	 * Applies exception filter to {@code IntConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntConsumer(Exceptions.log().passing().fromIntConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code IntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntConsumer} in a try-catch block
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
	 * Applies exception filter to {@code LongConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongConsumer(Exceptions.log().passing().fromLongConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code LongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongConsumer} in a try-catch block
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
	 * Applies exception filter to {@code DoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleConsumer(Exceptions.log().passing().fromDoubleConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code DoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleConsumer} in a try-catch block
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
	 * Applies exception filter to {@code BiConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiConsumer(Exceptions.log().passing().fromBiConsumer((t, u) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code BiConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code BiConsumer} in a try-catch block
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
	 * Applies exception filter to {@code ObjIntConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjIntConsumer(Exceptions.log().passing().fromObjIntConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ObjIntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code ObjIntConsumer} in a try-catch block
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
	 * Applies exception filter to {@code ObjLongConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjLongConsumer(Exceptions.log().passing().fromObjLongConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ObjLongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code ObjLongConsumer} in a try-catch block
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
	 * Applies exception filter to {@code ObjDoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingObjDoubleConsumer(Exceptions.log().passing().fromObjDoubleConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ObjDoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code ObjDoubleConsumer} in a try-catch block
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
	 * Applies exception filter to {@code Predicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingPredicate(Exceptions.log().passing().predicate(t -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code Predicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code Predicate} in a try-catch block
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
	 * Applies exception filter to {@code IntPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntPredicate(Exceptions.log().passing().fromIntPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code IntPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntPredicate} in a try-catch block
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
	 * Applies exception filter to {@code LongPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongPredicate(Exceptions.log().passing().fromLongPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code LongPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongPredicate} in a try-catch block
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
	 * Applies exception filter to {@code DoublePredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoublePredicate(Exceptions.log().passing().fromDoublePredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code DoublePredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoublePredicate} in a try-catch block
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
	 * Applies exception filter to {@code BiPredicate}.
	 * <p>
	 * If {@code predicate} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiPredicate(Exceptions.log().passing().fromBiPredicate((t, u) -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code BiPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code BiPredicate} in a try-catch block
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
	 * Applies exception filter to {@code Function}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingFunction(Exceptions.log().passing().function(t -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code Function} to wrap, usually a lambda
	 * @return wrapper that runs {@code Function} in a try-catch block
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
	 * Applies exception filter to {@code ToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToIntFunction(Exceptions.log().passing().fromToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToIntFunction} in a try-catch block
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
	 * Applies exception filter to {@code IntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntFunction(Exceptions.log().passing().fromIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code IntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntFunction} in a try-catch block
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
	 * Applies exception filter to {@code IntToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntToLongFunction(Exceptions.log().passing().fromIntToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code IntToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntToLongFunction} in a try-catch block
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
	 * Applies exception filter to {@code IntToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntToDoubleFunction(Exceptions.log().passing().fromIntToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code IntToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntToDoubleFunction} in a try-catch block
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
	 * Applies exception filter to {@code ToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToLongFunction(Exceptions.log().passing().fromToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToLongFunction} in a try-catch block
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
	 * Applies exception filter to {@code LongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongFunction(Exceptions.log().passing().fromLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code LongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongFunction} in a try-catch block
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
	 * Applies exception filter to {@code LongToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongToIntFunction(Exceptions.log().passing().fromLongToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code LongToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongToIntFunction} in a try-catch block
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
	 * Applies exception filter to {@code LongToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongToDoubleFunction(Exceptions.log().passing().fromLongToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code LongToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongToDoubleFunction} in a try-catch block
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
	 * Applies exception filter to {@code ToDoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleFunction(Exceptions.log().passing().fromToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToDoubleFunction} in a try-catch block
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
	 * Applies exception filter to {@code DoubleFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleFunction(Exceptions.log().passing().fromDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code DoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleFunction} in a try-catch block
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
	 * Applies exception filter to {@code DoubleToIntFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToIntFunction(Exceptions.log().passing().fromDoubleToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code DoubleToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleToIntFunction} in a try-catch block
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
	 * Applies exception filter to {@code DoubleToLongFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToLongFunction(Exceptions.log().passing().fromDoubleToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code DoubleToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleToLongFunction} in a try-catch block
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
	 * Applies exception filter to {@code UnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingUnaryOperator(Exceptions.log().passing().fromUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code UnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code UnaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code IntUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntUnaryOperator(Exceptions.log().passing().fromIntUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code IntUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntUnaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code LongUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongUnaryOperator(Exceptions.log().passing().fromLongUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code LongUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongUnaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code DoubleUnaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleUnaryOperator(Exceptions.log().passing().fromDoubleUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code DoubleUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleUnaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code BiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBiFunction(Exceptions.log().passing().fromBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code BiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code BiFunction} in a try-catch block
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
	 * Applies exception filter to {@code ToIntBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToIntBiFunction(Exceptions.log().passing().fromToIntBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToIntBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToIntBiFunction} in a try-catch block
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
	 * Applies exception filter to {@code ToLongBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToLongBiFunction(Exceptions.log().passing().fromToLongBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToLongBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToLongBiFunction} in a try-catch block
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
	 * Applies exception filter to {@code ToDoubleBiFunction}.
	 * <p>
	 * If {@code function} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleBiFunction(Exceptions.log().passing().fromToDoubleBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ToDoubleBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code ToDoubleBiFunction} in a try-catch block
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
	 * Applies exception filter to {@code BinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingBinaryOperator(Exceptions.log().passing().fromBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code BinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code BinaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code IntBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingIntBinaryOperator(Exceptions.log().passing().fromIntBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code IntBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code IntBinaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code LongBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingLongBinaryOperator(Exceptions.log().passing().fromLongBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code LongBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code LongBinaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code DoubleBinaryOperator}.
	 * <p>
	 * If {@code operator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleBinaryOperator(Exceptions.log().passing().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code DoubleBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code DoubleBinaryOperator} in a try-catch block
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
	 * Applies exception filter to {@code Comparator}.
	 * <p>
	 * If {@code comparator} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code comparator} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code methodTakingComparator(Exceptions.log().passing().comparator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param comparator
	 *            the {@code Comparator} to wrap, usually a lambda
	 * @return wrapper that runs {@code Comparator} in a try-catch block
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
	 * Applies exception filter to {@code CloseableScope}.
	 * <p>
	 * If {@code closeable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code closeable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code try (var scope = Exceptions.log().passing().closeable(openSomething()))}
	 * 
	 * @param closeable
	 *            the {@code CloseableScope} to wrap
	 * @return wrapper that runs {@code CloseableScope} in a try-catch block
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
	 * Filters exceptions while running {@code Runnable}.
	 * <p>
	 * If {@code runnable} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code runnable} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().run(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@code Runnable} to run, usually a lambda
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
	 * Filters exceptions while running {@code Supplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code Supplier} to run, usually a lambda
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
	 * Filters exceptions while running {@code IntSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsInt(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code IntSupplier} to run, usually a lambda
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
	 * Filters exceptions while running {@code LongSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsLong(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code LongSupplier} to run, usually a lambda
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
	 * Filters exceptions while running {@code DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsDouble(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code DoubleSupplier} to run, usually a lambda
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
	 * Filters exceptions while running {@code BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws an exception, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Method {@link #handle(Throwable)} is free to throw any replacement exception. If it returns, the original exception is rethrown.
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().getAsBoolean(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code BooleanSupplier} to run, usually a lambda
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
