// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.function.*;
import com.machinezoo.noexception.throwing.*;
import lombok.*;

/**
 * Represents downgrading policy for checked exceptions.
 * The exception policy is akin to a reusable {@code catch} block that catches checked exception and throws an unchecked one.
 * Method {@link #handle(Exception)} defines downgrading mechanism, typically by wrapping the checked exception in an unchecked one,
 * but there are special cases like {@link Exceptions#sneak()}, which downgrade only method signature without altering the exception itself.
 * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * <p>
 * Typical usage: {@code Exceptions.sneak().get(() -> my_throwing_lambda)}
 * <p>
 * {@code CheckedExceptionHandler} does not stop propagation of any exceptions (checked or unchecked).
 * {@link ExceptionHandler} is used for that purpose.
 * The two classes can be used together by first downgrading checked exceptions with {@code CheckedExceptionHandler}
 * and then applying exception handling policy with {@code ExceptionHandler}.
 * <p>
 * Combined usage: {@code Exceptions.log().get(Exceptions.sneak().supplier(() -> my_throwing_lambda)).orElse(fallback)}
 * <p>
 * All wrapping methods surround the functional interface with a try-catch block.
 * Only checked exceptions are handled. Unchecked exceptions are propagated to caller.
 * If the functional interface throws checked exception, the exception is caught and passed to {@link #handle(Exception)},
 * which converts it to an unchecked exception, which is then thrown.
 * <p>
 * Wrapping methods for all standard functional interfaces are provided.
 * Simple interfaces have short method names, like {@link #runnable(ThrowingRunnable)} or {@link #supplier(ThrowingSupplier)}.
 * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(ThrowingUnaryOperator)}.
 * Parameterless functional interfaces can be called directly by methods {@link #run(ThrowingRunnable)}, {@link #get(ThrowingSupplier)},
 * and the various {@code getAsX} variants.
 * All methods take throwing versions of standard functional interfaces, for example {@link ThrowingRunnable} or {@link ThrowingSupplier}.
 * 
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 * @see #handle(Exception)
 * @see Exceptions
 * @see ExceptionHandler
 */
public abstract class CheckedExceptionHandler {
	/**
	 * Convert checked exception into an unchecked one. This method must be defined in a derived class.
	 * Several implementations are provided by methods on {@link Exceptions} class.
	 * All other methods of the {@code CheckedExceptionHandler} call this method, but it can be also called directly.
	 * <p>
	 * This method represents reusable catch block that handles all checked exceptions in the same way.
	 * When invoked, it must somehow convert the checked exception into an unchecked one, usually by wrapping it.
	 * Caller is then expected to throw the returned exception.
	 * There can be special cases like {@link Exceptions#sneak()}, which don't return at all.
	 * <p>
	 * Callers should not pass in {@code RuntimeException} or other unchecked exceptions.
	 * This method might erroneously wrap such exceptions as if they are checked exceptions.
	 * Methods of this class never pass unchecked exceptions to this method.
	 * 
	 * @param exception
	 *            checked exception to convert
	 * @return converted unchecked exception
	 * @throws NullPointerException
	 *             if {@code exception} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public abstract RuntimeException handle(Exception exception);
	/**
	 * Initialize new {@code CheckedExceptionHandler}.
	 */
	protected CheckedExceptionHandler() {
	}
	/**
	 * Remove checked exceptions from method signature of {@code Runnable}.
	 * <p>
	 * If {@code runnable} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code runnable} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingRunnable(Exceptions.sneak().runnable(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@code ThrowingRunnable} to be converted, usually a lambda
	 * @return converted {@code Runnable} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final Runnable runnable(ThrowingRunnable runnable) {
		return new CheckedRunnable(runnable);
	}
	@RequiredArgsConstructor private final class CheckedRunnable implements Runnable {
		private final ThrowingRunnable runnable;
		@Override public void run() {
			try {
				runnable.run();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code Supplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code supplier} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingSupplier(Exceptions.sneak().supplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingSupplier} to be converted, usually a lambda
	 * @return converted {@code Supplier} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Supplier<T> supplier(ThrowingSupplier<T> supplier) {
		return new CheckedSupplier<T>(supplier);
	}
	@RequiredArgsConstructor private final class CheckedSupplier<T> implements Supplier<T> {
		private final ThrowingSupplier<T> supplier;
		@Override public T get() {
			try {
				return supplier.get();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code supplier} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntSupplier(Exceptions.sneak().fromIntSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingIntSupplier} to be converted, usually a lambda
	 * @return converted {@code IntSupplier} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntSupplier fromIntSupplier(ThrowingIntSupplier supplier) {
		return new CheckedIntSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedIntSupplier implements IntSupplier {
		private final ThrowingIntSupplier supplier;
		@Override public int getAsInt() {
			try {
				return supplier.getAsInt();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code supplier} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongSupplier(Exceptions.sneak().fromLongSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingLongSupplier} to be converted, usually a lambda
	 * @return converted {@code LongSupplier} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongSupplier fromLongSupplier(ThrowingLongSupplier supplier) {
		return new CheckedLongSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedLongSupplier implements LongSupplier {
		private final ThrowingLongSupplier supplier;
		@Override public long getAsLong() {
			try {
				return supplier.getAsLong();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code supplier} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleSupplier(Exceptions.sneak().fromDoubleSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingDoubleSupplier} to be converted, usually a lambda
	 * @return converted {@code DoubleSupplier} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleSupplier fromDoubleSupplier(ThrowingDoubleSupplier supplier) {
		return new CheckedDoubleSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedDoubleSupplier implements DoubleSupplier {
		private final ThrowingDoubleSupplier supplier;
		@Override public double getAsDouble() {
			try {
				return supplier.getAsDouble();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code supplier} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingBooleanSupplier(Exceptions.sneak().fromBooleanSupplier(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingBooleanSupplier} to be converted, usually a lambda
	 * @return converted {@code BooleanSupplier} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final BooleanSupplier fromBooleanSupplier(ThrowingBooleanSupplier supplier) {
		return new CheckedBooleanSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CheckedBooleanSupplier implements BooleanSupplier {
		private final ThrowingBooleanSupplier supplier;
		@Override public boolean getAsBoolean() {
			try {
				return supplier.getAsBoolean();
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code Consumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingConsumer(Exceptions.sneak().consumer(t -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingConsumer} to be converted, usually a lambda
	 * @return converted {@code Consumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Consumer<T> consumer(ThrowingConsumer<T> consumer) {
		return new CheckedConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedConsumer<T> implements Consumer<T> {
		private final ThrowingConsumer<T> consumer;
		@Override public void accept(T t) {
			try {
				consumer.accept(t);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntConsumer(Exceptions.sneak().fromIntConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingIntConsumer} to be converted, usually a lambda
	 * @return converted {@code IntConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntConsumer fromIntConsumer(ThrowingIntConsumer consumer) {
		return new CheckedIntConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedIntConsumer implements IntConsumer {
		private final ThrowingIntConsumer consumer;
		@Override public void accept(int value) {
			try {
				consumer.accept(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongConsumer(Exceptions.sneak().fromLongConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingLongConsumer} to be converted, usually a lambda
	 * @return converted {@code LongConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongConsumer fromLongConsumer(ThrowingLongConsumer consumer) {
		return new CheckedLongConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedLongConsumer implements LongConsumer {
		private final ThrowingLongConsumer consumer;
		@Override public void accept(long value) {
			try {
				consumer.accept(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleConsumer(Exceptions.sneak().fromDoubleConsumer(v -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingDoubleConsumer} to be converted, usually a lambda
	 * @return converted {@code DoubleConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleConsumer fromDoubleConsumer(ThrowingDoubleConsumer consumer) {
		return new CheckedDoubleConsumer(consumer);
	}
	@RequiredArgsConstructor private final class CheckedDoubleConsumer implements DoubleConsumer {
		private final ThrowingDoubleConsumer consumer;
		@Override public void accept(double value) {
			try {
				consumer.accept(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code BiConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingBiConsumer(Exceptions.sneak().fromBiConsumer((t, u) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingBiConsumer} to be converted, usually a lambda
	 * @return converted {@code BiConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> BiConsumer<T, U> fromBiConsumer(ThrowingBiConsumer<T, U> consumer) {
		return new CheckedBiConsumer<T, U>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedBiConsumer<T, U> implements BiConsumer<T, U> {
		private final ThrowingBiConsumer<T, U> consumer;
		@Override public void accept(T t, U u) {
			try {
				consumer.accept(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ObjIntConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingObjIntConsumer(Exceptions.sneak().fromObjIntConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingObjIntConsumer} to be converted, usually a lambda
	 * @return converted {@code ObjIntConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjIntConsumer<T> fromObjIntConsumer(ThrowingObjIntConsumer<T> consumer) {
		return new CheckedObjIntConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjIntConsumer<T> implements ObjIntConsumer<T> {
		private final ThrowingObjIntConsumer<T> consumer;
		@Override public void accept(T t, int value) {
			try {
				consumer.accept(t, value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ObjLongConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingObjLongConsumer(Exceptions.sneak().fromObjLongConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingObjLongConsumer} to be converted, usually a lambda
	 * @return converted {@code ObjLongConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjLongConsumer<T> fromObjLongConsumer(ThrowingObjLongConsumer<T> consumer) {
		return new CheckedObjLongConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjLongConsumer<T> implements ObjLongConsumer<T> {
		private final ThrowingObjLongConsumer<T> consumer;
		@Override public void accept(T t, long value) {
			try {
				consumer.accept(t, value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ObjDoubleConsumer}.
	 * <p>
	 * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code consumer} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingObjDoubleConsumer(Exceptions.sneak().fromObjDoubleConsumer((t, v) -> my_throwing_lambda))}
	 * 
	 * @param consumer
	 *            the {@code ThrowingObjDoubleConsumer} to be converted, usually a lambda
	 * @return converted {@code ObjDoubleConsumer} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ThrowingObjDoubleConsumer<T> consumer) {
		return new CheckedObjDoubleConsumer<T>(consumer);
	}
	@RequiredArgsConstructor private final class CheckedObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
		private final ThrowingObjDoubleConsumer<T> consumer;
		@Override public void accept(T t, double value) {
			try {
				consumer.accept(t, value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code Predicate}.
	 * <p>
	 * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code predicate} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingPredicate(Exceptions.sneak().predicate(t -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code ThrowingPredicate} to be converted, usually a lambda
	 * @return converted {@code Predicate} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Predicate<T> predicate(ThrowingPredicate<T> predicate) {
		return new CheckedPredicate<T>(predicate);
	}
	@RequiredArgsConstructor private final class CheckedPredicate<T> implements Predicate<T> {
		private final ThrowingPredicate<T> predicate;
		@Override public boolean test(T t) {
			try {
				return predicate.test(t);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntPredicate}.
	 * <p>
	 * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code predicate} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntPredicate(Exceptions.sneak().fromIntPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code ThrowingIntPredicate} to be converted, usually a lambda
	 * @return converted {@code IntPredicate} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntPredicate fromIntPredicate(ThrowingIntPredicate predicate) {
		return new CheckedIntPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedIntPredicate implements IntPredicate {
		private final ThrowingIntPredicate predicate;
		@Override public boolean test(int value) {
			try {
				return predicate.test(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongPredicate}.
	 * <p>
	 * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code predicate} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongPredicate(Exceptions.sneak().fromLongPredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code ThrowingLongPredicate} to be converted, usually a lambda
	 * @return converted {@code LongPredicate} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongPredicate fromLongPredicate(ThrowingLongPredicate predicate) {
		return new CheckedLongPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedLongPredicate implements LongPredicate {
		private final ThrowingLongPredicate predicate;
		@Override public boolean test(long value) {
			try {
				return predicate.test(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoublePredicate}.
	 * <p>
	 * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code predicate} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoublePredicate(Exceptions.sneak().fromDoublePredicate(v -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code ThrowingDoublePredicate} to be converted, usually a lambda
	 * @return converted {@code DoublePredicate} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoublePredicate fromDoublePredicate(ThrowingDoublePredicate predicate) {
		return new CheckedDoublePredicate(predicate);
	}
	@RequiredArgsConstructor private final class CheckedDoublePredicate implements DoublePredicate {
		private final ThrowingDoublePredicate predicate;
		@Override public boolean test(double value) {
			try {
				return predicate.test(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code BiPredicate}.
	 * <p>
	 * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code predicate} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingBiPredicate(Exceptions.sneak().fromBiPredicate((t, u) -> my_throwing_lambda))}
	 * 
	 * @param predicate
	 *            the {@code ThrowingBiPredicate} to be converted, usually a lambda
	 * @return converted {@code BiPredicate} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> BiPredicate<T, U> fromBiPredicate(ThrowingBiPredicate<T, U> predicate) {
		return new CheckedBiPredicate<T, U>(predicate);
	}
	@RequiredArgsConstructor private final class CheckedBiPredicate<T, U> implements BiPredicate<T, U> {
		private final ThrowingBiPredicate<T, U> predicate;
		@Override public boolean test(T t, U u) {
			try {
				return predicate.test(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code Function}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingFunction(Exceptions.sneak().function(t -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingFunction} to be converted, usually a lambda
	 * @return converted {@code Function} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, R> Function<T, R> function(ThrowingFunction<T, R> function) {
		return new CheckedFunction<T, R>(function);
	}
	@RequiredArgsConstructor private final class CheckedFunction<T, R> implements Function<T, R> {
		private final ThrowingFunction<T, R> function;
		@Override public R apply(T t) {
			try {
				return function.apply(t);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToIntFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToIntFunction(Exceptions.sneak().fromToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToIntFunction} to be converted, usually a lambda
	 * @return converted {@code ToIntFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToIntFunction<T> fromToIntFunction(ThrowingToIntFunction<T> function) {
		return new CheckedToIntFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToIntFunction<T> implements ToIntFunction<T> {
		private final ThrowingToIntFunction<T> function;
		@Override public int applyAsInt(T value) {
			try {
				return function.applyAsInt(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntFunction(Exceptions.sneak().fromIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingIntFunction} to be converted, usually a lambda
	 * @return converted {@code IntFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> IntFunction<R> fromIntFunction(ThrowingIntFunction<R> function) {
		return new CheckedIntFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedIntFunction<R> implements IntFunction<R> {
		private final ThrowingIntFunction<R> function;
		@Override public R apply(int value) {
			try {
				return function.apply(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntToLongFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntToLongFunction(Exceptions.sneak().fromIntToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingIntToLongFunction} to be converted, usually a lambda
	 * @return converted {@code IntToLongFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntToLongFunction fromIntToLongFunction(ThrowingIntToLongFunction function) {
		return new CheckedIntToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedIntToLongFunction implements IntToLongFunction {
		private final ThrowingIntToLongFunction function;
		@Override public long applyAsLong(int value) {
			try {
				return function.applyAsLong(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntToDoubleFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntToDoubleFunction(Exceptions.sneak().fromIntToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingIntToDoubleFunction} to be converted, usually a lambda
	 * @return converted {@code IntToDoubleFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntToDoubleFunction fromIntToDoubleFunction(ThrowingIntToDoubleFunction function) {
		return new CheckedIntToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedIntToDoubleFunction implements IntToDoubleFunction {
		private final ThrowingIntToDoubleFunction function;
		@Override public double applyAsDouble(int value) {
			try {
				return function.applyAsDouble(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToLongFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToLongFunction(Exceptions.sneak().fromToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToLongFunction} to be converted, usually a lambda
	 * @return converted {@code ToLongFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToLongFunction<T> fromToLongFunction(ThrowingToLongFunction<T> function) {
		return new CheckedToLongFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToLongFunction<T> implements ToLongFunction<T> {
		private final ThrowingToLongFunction<T> function;
		@Override public long applyAsLong(T value) {
			try {
				return function.applyAsLong(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongFunction(Exceptions.sneak().fromLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingLongFunction} to be converted, usually a lambda
	 * @return converted {@code LongFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> LongFunction<R> fromLongFunction(ThrowingLongFunction<R> function) {
		return new CheckedLongFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedLongFunction<R> implements LongFunction<R> {
		private final ThrowingLongFunction<R> function;
		@Override public R apply(long value) {
			try {
				return function.apply(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongToIntFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongToIntFunction(Exceptions.sneak().fromLongToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingLongToIntFunction} to be converted, usually a lambda
	 * @return converted {@code LongToIntFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongToIntFunction fromLongToIntFunction(ThrowingLongToIntFunction function) {
		return new CheckedLongToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedLongToIntFunction implements LongToIntFunction {
		private final ThrowingLongToIntFunction function;
		@Override public int applyAsInt(long value) {
			try {
				return function.applyAsInt(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongToDoubleFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongToDoubleFunction(Exceptions.sneak().fromLongToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingLongToDoubleFunction} to be converted, usually a lambda
	 * @return converted {@code LongToDoubleFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongToDoubleFunction fromLongToDoubleFunction(ThrowingLongToDoubleFunction function) {
		return new CheckedLongToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedLongToDoubleFunction implements LongToDoubleFunction {
		private final ThrowingLongToDoubleFunction function;
		@Override public double applyAsDouble(long value) {
			try {
				return function.applyAsDouble(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToDoubleFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleFunction(Exceptions.sneak().fromToDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToDoubleFunction} to be converted, usually a lambda
	 * @return converted {@code ToDoubleFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ToDoubleFunction<T> fromToDoubleFunction(ThrowingToDoubleFunction<T> function) {
		return new CheckedToDoubleFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CheckedToDoubleFunction<T> implements ToDoubleFunction<T> {
		private final ThrowingToDoubleFunction<T> function;
		@Override public double applyAsDouble(T value) {
			try {
				return function.applyAsDouble(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleFunction(Exceptions.sneak().fromDoubleFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingDoubleFunction} to be converted, usually a lambda
	 * @return converted {@code DoubleFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> DoubleFunction<R> fromDoubleFunction(ThrowingDoubleFunction<R> function) {
		return new CheckedDoubleFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleFunction<R> implements DoubleFunction<R> {
		private final ThrowingDoubleFunction<R> function;
		@Override public R apply(double value) {
			try {
				return function.apply(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleToIntFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToIntFunction(Exceptions.sneak().fromDoubleToIntFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingDoubleToIntFunction} to be converted, usually a lambda
	 * @return converted {@code DoubleToIntFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleToIntFunction fromDoubleToIntFunction(ThrowingDoubleToIntFunction function) {
		return new CheckedDoubleToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleToIntFunction implements DoubleToIntFunction {
		private final ThrowingDoubleToIntFunction function;
		@Override public int applyAsInt(double value) {
			try {
				return function.applyAsInt(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleToLongFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleToLongFunction(Exceptions.sneak().fromDoubleToLongFunction(v -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingDoubleToLongFunction} to be converted, usually a lambda
	 * @return converted {@code DoubleToLongFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleToLongFunction fromDoubleToLongFunction(ThrowingDoubleToLongFunction function) {
		return new CheckedDoubleToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CheckedDoubleToLongFunction implements DoubleToLongFunction {
		private final ThrowingDoubleToLongFunction function;
		@Override public long applyAsLong(double value) {
			try {
				return function.applyAsLong(value);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code UnaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingUnaryOperator(Exceptions.sneak().fromUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingUnaryOperator} to be converted, usually a lambda
	 * @return converted {@code UnaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> UnaryOperator<T> fromUnaryOperator(ThrowingUnaryOperator<T> operator) {
		return new CheckedUnaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CheckedUnaryOperator<T> implements UnaryOperator<T> {
		private final ThrowingUnaryOperator<T> operator;
		@Override public T apply(T operand) {
			try {
				return operator.apply(operand);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntUnaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntUnaryOperator(Exceptions.sneak().fromIntUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingIntUnaryOperator} to be converted, usually a lambda
	 * @return converted {@code IntUnaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntUnaryOperator fromIntUnaryOperator(ThrowingIntUnaryOperator operator) {
		return new CheckedIntUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedIntUnaryOperator implements IntUnaryOperator {
		private final ThrowingIntUnaryOperator operator;
		@Override public int applyAsInt(int operand) {
			try {
				return operator.applyAsInt(operand);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongUnaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongUnaryOperator(Exceptions.sneak().fromLongUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingLongUnaryOperator} to be converted, usually a lambda
	 * @return converted {@code LongUnaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongUnaryOperator fromLongUnaryOperator(ThrowingLongUnaryOperator operator) {
		return new CheckedLongUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedLongUnaryOperator implements LongUnaryOperator {
		private final ThrowingLongUnaryOperator operator;
		@Override public long applyAsLong(long operand) {
			try {
				return operator.applyAsLong(operand);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleUnaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleUnaryOperator(Exceptions.sneak().fromDoubleUnaryOperator(o -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingDoubleUnaryOperator} to be converted, usually a lambda
	 * @return converted {@code DoubleUnaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleUnaryOperator fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator operator) {
		return new CheckedDoubleUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedDoubleUnaryOperator implements DoubleUnaryOperator {
		private final ThrowingDoubleUnaryOperator operator;
		@Override public double applyAsDouble(double operand) {
			try {
				return operator.applyAsDouble(operand);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code BiFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingBiFunction(Exceptions.sneak().fromBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingBiFunction} to be converted, usually a lambda
	 * @return converted {@code BiFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U, R> BiFunction<T, U, R> fromBiFunction(ThrowingBiFunction<T, U, R> function) {
		return new CheckedBiFunction<T, U, R>(function);
	}
	@RequiredArgsConstructor private final class CheckedBiFunction<T, U, R> implements BiFunction<T, U, R> {
		private final ThrowingBiFunction<T, U, R> function;
		@Override public R apply(T t, U u) {
			try {
				return function.apply(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToIntBiFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToIntBiFunction(Exceptions.sneak().fromToIntBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToIntBiFunction} to be converted, usually a lambda
	 * @return converted {@code ToIntBiFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToIntBiFunction<T, U> fromToIntBiFunction(ThrowingToIntBiFunction<T, U> function) {
		return new CheckedToIntBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
		private final ThrowingToIntBiFunction<T, U> function;
		@Override public int applyAsInt(T t, U u) {
			try {
				return function.applyAsInt(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToLongBiFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToLongBiFunction(Exceptions.sneak().fromToLongBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToLongBiFunction} to be converted, usually a lambda
	 * @return converted {@code ToLongBiFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToLongBiFunction<T, U> fromToLongBiFunction(ThrowingToLongBiFunction<T, U> function) {
		return new CheckedToLongBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
		private final ThrowingToLongBiFunction<T, U> function;
		@Override public long applyAsLong(T t, U u) {
			try {
				return function.applyAsLong(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code ToDoubleBiFunction}.
	 * <p>
	 * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code function} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingToDoubleBiFunction(Exceptions.sneak().fromToDoubleBiFunction((t, u) -> my_throwing_lambda))}
	 * 
	 * @param function
	 *            the {@code ThrowingToDoubleBiFunction} to be converted, usually a lambda
	 * @return converted {@code ToDoubleBiFunction} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> ToDoubleBiFunction<T, U> fromToDoubleBiFunction(ThrowingToDoubleBiFunction<T, U> function) {
		return new CheckedToDoubleBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CheckedToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
		private final ThrowingToDoubleBiFunction<T, U> function;
		@Override public double applyAsDouble(T t, U u) {
			try {
				return function.applyAsDouble(t, u);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code BinaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingBinaryOperator(Exceptions.sneak().fromBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingBinaryOperator} to be converted, usually a lambda
	 * @return converted {@code BinaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> BinaryOperator<T> fromBinaryOperator(ThrowingBinaryOperator<T> operator) {
		return new CheckedBinaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CheckedBinaryOperator<T> implements BinaryOperator<T> {
		private final ThrowingBinaryOperator<T> operator;
		@Override public T apply(T left, T right) {
			try {
				return operator.apply(left, right);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code IntBinaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingIntBinaryOperator(Exceptions.sneak().fromIntBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingIntBinaryOperator} to be converted, usually a lambda
	 * @return converted {@code IntBinaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final IntBinaryOperator fromIntBinaryOperator(ThrowingIntBinaryOperator operator) {
		return new CheckedIntBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedIntBinaryOperator implements IntBinaryOperator {
		private final ThrowingIntBinaryOperator operator;
		@Override public int applyAsInt(int left, int right) {
			try {
				return operator.applyAsInt(left, right);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code LongBinaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingLongBinaryOperator(Exceptions.sneak().fromLongBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingLongBinaryOperator} to be converted, usually a lambda
	 * @return converted {@code LongBinaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final LongBinaryOperator fromLongBinaryOperator(ThrowingLongBinaryOperator operator) {
		return new CheckedLongBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedLongBinaryOperator implements LongBinaryOperator {
		private final ThrowingLongBinaryOperator operator;
		@Override public long applyAsLong(long left, long right) {
			try {
				return operator.applyAsLong(left, right);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Remove checked exceptions from method signature of {@code DoubleBinaryOperator}.
	 * <p>
	 * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * Null {@code operator} is silently wrapped and causes {@code NullPointerException} when executed.
	 * <p>
	 * Typical usage: {@code methodTakingDoubleBinaryOperator(Exceptions.sneak().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda))}
	 * 
	 * @param operator
	 *            the {@code ThrowingDoubleBinaryOperator} to be converted, usually a lambda
	 * @return converted {@code DoubleBinaryOperator} free of checked exceptions
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleBinaryOperator fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator operator) {
		return new CheckedDoubleBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CheckedDoubleBinaryOperator implements DoubleBinaryOperator {
		private final ThrowingDoubleBinaryOperator operator;
		@Override public double applyAsDouble(double left, double right) {
			try {
				return operator.applyAsDouble(left, right);
			} catch (RuntimeException exception) {
				throw exception;
			} catch (Exception exception) {
				throw handle(exception);
			}
		}
	}
	/**
	 * Filter out checked exceptions while running {@code Runnable}.
	 * <p>
	 * If {@code runnable} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().run(() -> my_throwing_lambda))}
	 * 
	 * @param runnable
	 *            the {@code ThrowingRunnable} to be converted, usually a lambda
	 * @throws NullPointerException
	 *             if {@code runnable} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final void run(ThrowingRunnable runnable) {
		try {
			runnable.run();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
	/**
	 * Filter out checked exceptions while running {@code Supplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().get(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingSupplier} to be converted, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> T get(ThrowingSupplier<T> supplier) {
		try {
			return supplier.get();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
	/**
	 * Filter out checked exceptions while running {@code IntSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().getAsInt(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingIntSupplier} to be converted, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final int getAsInt(ThrowingIntSupplier supplier) {
		try {
			return supplier.getAsInt();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
	/**
	 * Filter out checked exceptions while running {@code LongSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().getAsLong(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingLongSupplier} to be converted, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final long getAsLong(ThrowingLongSupplier supplier) {
		try {
			return supplier.getAsLong();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
	/**
	 * Filter out checked exceptions while running {@code DoubleSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().getAsDouble(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingDoubleSupplier} to be converted, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final double getAsDouble(ThrowingDoubleSupplier supplier) {
		try {
			return supplier.getAsDouble();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
	/**
	 * Filter out checked exceptions while running {@code BooleanSupplier}.
	 * <p>
	 * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
	 * which usually converts it to an unchecked exception, which is then thrown by this method.
	 * <p>
	 * Typical usage: {@code Exceptions.sneak().getAsBoolean(() -> my_throwing_lambda))}
	 * 
	 * @param supplier
	 *            the {@code ThrowingBooleanSupplier} to be converted, usually a lambda
	 * @return value returned from {@code supplier}
	 * @throws NullPointerException
	 *             if {@code supplier} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final boolean getAsBoolean(ThrowingBooleanSupplier supplier) {
		try {
			return supplier.getAsBoolean();
		} catch (RuntimeException exception) {
			throw exception;
		} catch (Exception exception) {
			throw handle(exception);
		}
	}
}
