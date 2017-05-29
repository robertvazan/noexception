// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.optional.*;
import lombok.*;

/**
 * Represents exception handling policy.
 * Methods of this class apply the exception policy to functional interfaces by wrapping them in a try-catch block.
 * Method {@link #handle(Throwable)} defines the exception handling policy when implemented in derived class.
 * See <a href="https://noexception.machinezoo.com/">NoException tutorial</a>.
 * <p>
 * Wrapping methods for all standard functional interfaces are provided.
 * Simple interfaces have short method names, e.g. {@link #runnable(Runnable)} or {@link #supplier(Supplier)}.
 * Interfaces with longer names have methods that follow {@code fromX} naming pattern, e.g. {@link #fromUnaryOperator(UnaryOperator)}.
 * Parameterless functional interfaces can be called directly by methods {@link #run(Runnable)}, {@link #get(Supplier)},
 * and the various {@code getAsX} variants.
 * <p>
 * All wrapping methods surround the functional interface with a try-catch block.
 * If the functional interface throws, the exception is caught and passed to {@link #handle(Throwable)}.
 * {@code NullPointerException} from null functional interface is caught too.
 * Unless {@link #handle(Throwable)} requests a rethrow, void functional interfaces complete silently
 * while non-void functional interfaces return empty {@link Optional}.
 * <p>
 * All non-void wrappers conform to some {@code OptionalX} functional interface, e.g. {@link OptionalSupplier},
 * that is identical to its non-optional variant from JDK except it returns {@code Optional} instead of raw value.
 * This {@code Optional} is empty in case of exception.
 * Callers can use {@link Optional#orElse(Object)} and {@link Optional#orElseGet(Supplier)} and their
 * equivalents on {@code OptionalX} interfaces to provide fallback values.
 * 
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 * @see #handle(Throwable)
 * @see Exceptions
 * @see OptionalSupplier
 * @see Optional
 * @see CheckedExceptionHandler
 */
public abstract class ExceptionHandler {
	/**
	 * Handle exception in a generic way. This method must be defined in a derived class.
	 * Several implementations are provided by methods on {@link Exceptions} class.
	 * All other methods of the {@code ExceptionHandler} call this method, but it can be also called directly.
	 * <p>
	 * This method represents reusable catch block that handles all exceptions in the same way.
	 * When invoked, it must somehow handle the provided exception, e.g. by logging it.
	 * <p>
	 * This method does not have to handle all exceptions.
	 * It can indicate through return value whether it has accepted or rejected the exception.
	 * When an exception is rejected, caller of this method is expected to rethrow the exception.
	 * All other methods of this class fulfill this requirement.
	 * 
	 * @param exception
	 *            the exception to handle
	 * @return {@code true} when exception is handled, {@code false} if the exception should be rethrown
	 * @throws NullPointerException
	 *             if {@code exception} is {@code null}
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public abstract boolean handle(Throwable exception);
	/**
	 * Wraps {@code Runnable} in a try-catch block.
	 * <p>
	 * If {@code runnable} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code runnable} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().runnable(() -> my_throwing_lambda)}
	 * 
	 * @param runnable
	 *            the {@code Runnable} to wrap, usually a lambda
	 * @return wrapper that runs {@code runnable} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code Supplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().supplier(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code Supplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalSupplier<T> supplier(Supplier<T> supplier) {
		return new CatchingSupplier<T>(supplier);
	}
	@RequiredArgsConstructor private final class CatchingSupplier<T> implements OptionalSupplier<T> {
		private final Supplier<T> supplier;
		@Override public Optional<T> get() {
			try {
				return Optional.ofNullable(supplier.get());
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntSupplier(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code IntSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntSupplier fromIntSupplier(IntSupplier supplier) {
		return new CatchingIntSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingIntSupplier implements OptionalIntSupplier {
		private final IntSupplier supplier;
		@Override public OptionalInt get() {
			try {
				return OptionalInt.of(supplier.getAsInt());
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongSupplier(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code LongSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongSupplier fromLongSupplier(LongSupplier supplier) {
		return new CatchingLongSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingLongSupplier implements OptionalLongSupplier {
		private final LongSupplier supplier;
		@Override public OptionalLong get() {
			try {
				return OptionalLong.of(supplier.getAsLong());
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleSupplier(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code DoubleSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleSupplier fromDoubleSupplier(DoubleSupplier supplier) {
		return new CatchingDoubleSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingDoubleSupplier implements OptionalDoubleSupplier {
		private final DoubleSupplier supplier;
		@Override public OptionalDouble get() {
			try {
				return OptionalDouble.of(supplier.getAsDouble());
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code BooleanSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBooleanSupplier(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code BooleanSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalBooleanSupplier fromBooleanSupplier(BooleanSupplier supplier) {
		return new CatchingBooleanSupplier(supplier);
	}
	@RequiredArgsConstructor private final class CatchingBooleanSupplier implements OptionalBooleanSupplier {
		private final BooleanSupplier supplier;
		@Override public OptionalBoolean get() {
			try {
				return OptionalBoolean.of(supplier.getAsBoolean());
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code Consumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().consumer(t -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code Consumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code IntConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code IntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code LongConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code LongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code DoubleConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code DoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code BiConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiConsumer((t, u) -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code BiConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code ObjIntConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjIntConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code ObjIntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code ObjLongConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjLongConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code ObjLongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code ObjDoubleConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code consumer} is caught too.
	 * Wrapper then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjDoubleConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@code ObjDoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
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
	/**
	 * Wraps {@code Predicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().predicate(t -> my_throwing_lambda)}
	 * 
	 * @param predicate
	 *            the {@code Predicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalPredicate<T> predicate(Predicate<T> predicate) {
		return new CatchingPredicate<T>(predicate);
	}
	@RequiredArgsConstructor private final class CatchingPredicate<T> implements OptionalPredicate<T> {
		private final Predicate<T> predicate;
		@Override public OptionalBoolean test(T t) {
			try {
				return OptionalBoolean.of(predicate.test(t));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntPredicate(v -> my_throwing_lambda)}
	 * 
	 * @param predicate
	 *            the {@code IntPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntPredicate fromIntPredicate(IntPredicate predicate) {
		return new CatchingIntPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingIntPredicate implements OptionalIntPredicate {
		private final IntPredicate predicate;
		@Override public OptionalBoolean test(int value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongPredicate(v -> my_throwing_lambda)}
	 * 
	 * @param predicate
	 *            the {@code LongPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongPredicate fromLongPredicate(LongPredicate predicate) {
		return new CatchingLongPredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingLongPredicate implements OptionalLongPredicate {
		private final LongPredicate predicate;
		@Override public OptionalBoolean test(long value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoublePredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoublePredicate(v -> my_throwing_lambda)}
	 * 
	 * @param predicate
	 *            the {@code DoublePredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoublePredicate fromDoublePredicate(DoublePredicate predicate) {
		return new CatchingDoublePredicate(predicate);
	}
	@RequiredArgsConstructor private final class CatchingDoublePredicate implements OptionalDoublePredicate {
		private final DoublePredicate predicate;
		@Override public OptionalBoolean test(double value) {
			try {
				return OptionalBoolean.of(predicate.test(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code BiPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code predicate} is caught too.
	 * Wrapper then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiPredicate((t, u) -> my_throwing_lambda)}
	 * 
	 * @param predicate
	 *            the {@code BiPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalBiPredicate<T, U> fromBiPredicate(BiPredicate<T, U> predicate) {
		return new CatchingBiPredicate<T, U>(predicate);
	}
	@RequiredArgsConstructor private final class CatchingBiPredicate<T, U> implements OptionalBiPredicate<T, U> {
		private final BiPredicate<T, U> predicate;
		@Override public OptionalBoolean test(T t, U u) {
			try {
				return OptionalBoolean.of(predicate.test(t, u));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalBoolean.empty();
			}
		}
	}
	/**
	 * Wraps {@code Function} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().function(t -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code Function} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, R> OptionalFunction<T, R> function(Function<T, R> function) {
		return new CatchingFunction<T, R>(function);
	}
	@RequiredArgsConstructor private final class CatchingFunction<T, R> implements OptionalFunction<T, R> {
		private final Function<T, R> function;
		@Override public Optional<R> apply(T t) {
			try {
				return Optional.ofNullable(function.apply(t));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToIntFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToIntFunction<T> fromToIntFunction(ToIntFunction<T> function) {
		return new CatchingToIntFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToIntFunction<T> implements OptionalToIntFunction<T> {
		private final ToIntFunction<T> function;
		@Override public OptionalInt apply(T value) {
			try {
				return OptionalInt.of(function.applyAsInt(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code IntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalIntFunction<R> fromIntFunction(IntFunction<R> function) {
		return new CatchingIntFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingIntFunction<R> implements OptionalIntFunction<R> {
		private final IntFunction<R> function;
		@Override public Optional<R> apply(int value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntToLongFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code IntToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntToLongFunction fromIntToLongFunction(IntToLongFunction function) {
		return new CatchingIntToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingIntToLongFunction implements OptionalIntToLongFunction {
		private final IntToLongFunction function;
		@Override public OptionalLong apply(int value) {
			try {
				return OptionalLong.of(function.applyAsLong(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntToDoubleFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code IntToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntToDoubleFunction fromIntToDoubleFunction(IntToDoubleFunction function) {
		return new CatchingIntToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingIntToDoubleFunction implements OptionalIntToDoubleFunction {
		private final IntToDoubleFunction function;
		@Override public OptionalDouble apply(int value) {
			try {
				return OptionalDouble.of(function.applyAsDouble(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToLongFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToLongFunction<T> fromToLongFunction(ToLongFunction<T> function) {
		return new CatchingToLongFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToLongFunction<T> implements OptionalToLongFunction<T> {
		private final ToLongFunction<T> function;
		@Override public OptionalLong apply(T value) {
			try {
				return OptionalLong.of(function.applyAsLong(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code LongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalLongFunction<R> fromLongFunction(LongFunction<R> function) {
		return new CatchingLongFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingLongFunction<R> implements OptionalLongFunction<R> {
		private final LongFunction<R> function;
		@Override public Optional<R> apply(long value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongToIntFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code LongToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongToIntFunction fromLongToIntFunction(LongToIntFunction function) {
		return new CatchingLongToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingLongToIntFunction implements OptionalLongToIntFunction {
		private final LongToIntFunction function;
		@Override public OptionalInt apply(long value) {
			try {
				return OptionalInt.of(function.applyAsInt(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongToDoubleFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code LongToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongToDoubleFunction fromLongToDoubleFunction(LongToDoubleFunction function) {
		return new CatchingLongToDoubleFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingLongToDoubleFunction implements OptionalLongToDoubleFunction {
		private final LongToDoubleFunction function;
		@Override public OptionalDouble apply(long value) {
			try {
				return OptionalDouble.of(function.applyAsDouble(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToDoubleFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToDoubleFunction<T> fromToDoubleFunction(ToDoubleFunction<T> function) {
		return new CatchingToDoubleFunction<T>(function);
	}
	@RequiredArgsConstructor private final class CatchingToDoubleFunction<T> implements OptionalToDoubleFunction<T> {
		private final ToDoubleFunction<T> function;
		@Override public OptionalDouble apply(T value) {
			try {
				return OptionalDouble.of(function.applyAsDouble(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code DoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalDoubleFunction<R> fromDoubleFunction(DoubleFunction<R> function) {
		return new CatchingDoubleFunction<R>(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleFunction<R> implements OptionalDoubleFunction<R> {
		private final DoubleFunction<R> function;
		@Override public Optional<R> apply(double value) {
			try {
				return Optional.ofNullable(function.apply(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleToIntFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code DoubleToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleToIntFunction fromDoubleToIntFunction(DoubleToIntFunction function) {
		return new CatchingDoubleToIntFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleToIntFunction implements OptionalDoubleToIntFunction {
		private final DoubleToIntFunction function;
		@Override public OptionalInt apply(double value) {
			try {
				return OptionalInt.of(function.applyAsInt(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleToLongFunction(v -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code DoubleToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleToLongFunction fromDoubleToLongFunction(DoubleToLongFunction function) {
		return new CatchingDoubleToLongFunction(function);
	}
	@RequiredArgsConstructor private final class CatchingDoubleToLongFunction implements OptionalDoubleToLongFunction {
		private final DoubleToLongFunction function;
		@Override public OptionalLong apply(double value) {
			try {
				return OptionalLong.of(function.applyAsLong(value));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code UnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromUnaryOperator(o -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code UnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalUnaryOperator<T> fromUnaryOperator(UnaryOperator<T> operator) {
		return new CatchingUnaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CatchingUnaryOperator<T> implements OptionalUnaryOperator<T> {
		private final UnaryOperator<T> operator;
		@Override public Optional<T> apply(T operand) {
			try {
				return Optional.ofNullable(operator.apply(operand));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntUnaryOperator(o -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code IntUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntUnaryOperator fromIntUnaryOperator(IntUnaryOperator operator) {
		return new CatchingIntUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingIntUnaryOperator implements OptionalIntUnaryOperator {
		private final IntUnaryOperator operator;
		@Override public OptionalInt apply(int operand) {
			try {
				return OptionalInt.of(operator.applyAsInt(operand));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongUnaryOperator(o -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code LongUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongUnaryOperator fromLongUnaryOperator(LongUnaryOperator operator) {
		return new CatchingLongUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingLongUnaryOperator implements OptionalLongUnaryOperator {
		private final LongUnaryOperator operator;
		@Override public OptionalLong apply(long operand) {
			try {
				return OptionalLong.of(operator.applyAsLong(operand));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleUnaryOperator(o -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code DoubleUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleUnaryOperator fromDoubleUnaryOperator(DoubleUnaryOperator operator) {
		return new CatchingDoubleUnaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingDoubleUnaryOperator implements OptionalDoubleUnaryOperator {
		private final DoubleUnaryOperator operator;
		@Override public OptionalDouble apply(double operand) {
			try {
				return OptionalDouble.of(operator.applyAsDouble(operand));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code BiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiFunction((t, u) -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code BiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U, R> OptionalBiFunction<T, U, R> fromBiFunction(BiFunction<T, U, R> function) {
		return new CatchingBiFunction<T, U, R>(function);
	}
	@RequiredArgsConstructor private final class CatchingBiFunction<T, U, R> implements OptionalBiFunction<T, U, R> {
		private final BiFunction<T, U, R> function;
		@Override public Optional<R> apply(T t, U u) {
			try {
				return Optional.ofNullable(function.apply(t, u));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToIntBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToIntBiFunction((t, u) -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToIntBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToIntBiFunction<T, U> fromToIntBiFunction(ToIntBiFunction<T, U> function) {
		return new CatchingToIntBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToIntBiFunction<T, U> implements OptionalToIntBiFunction<T, U> {
		private final ToIntBiFunction<T, U> function;
		@Override public OptionalInt apply(T t, U u) {
			try {
				return OptionalInt.of(function.applyAsInt(t, u));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToLongBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToLongBiFunction((t, u) -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToLongBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToLongBiFunction<T, U> fromToLongBiFunction(ToLongBiFunction<T, U> function) {
		return new CatchingToLongBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToLongBiFunction<T, U> implements OptionalToLongBiFunction<T, U> {
		private final ToLongBiFunction<T, U> function;
		@Override public OptionalLong apply(T t, U u) {
			try {
				return OptionalLong.of(function.applyAsLong(t, u));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code ToDoubleBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code function} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToDoubleBiFunction((t, u) -> my_throwing_lambda)}
	 * 
	 * @param function
	 *            the {@code ToDoubleBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToDoubleBiFunction<T, U> fromToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
		return new CatchingToDoubleBiFunction<T, U>(function);
	}
	@RequiredArgsConstructor private final class CatchingToDoubleBiFunction<T, U> implements OptionalToDoubleBiFunction<T, U> {
		private final ToDoubleBiFunction<T, U> function;
		@Override public OptionalDouble apply(T t, U u) {
			try {
				return OptionalDouble.of(function.applyAsDouble(t, u));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Wraps {@code BinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBinaryOperator((l, r) -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code BinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalBinaryOperator<T> fromBinaryOperator(BinaryOperator<T> operator) {
		return new CatchingBinaryOperator<T>(operator);
	}
	@RequiredArgsConstructor private final class CatchingBinaryOperator<T> implements OptionalBinaryOperator<T> {
		private final BinaryOperator<T> operator;
		@Override public Optional<T> apply(T left, T right) {
			try {
				return Optional.ofNullable(operator.apply(left, right));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return Optional.empty();
			}
		}
	}
	/**
	 * Wraps {@code IntBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntBinaryOperator((l, r) -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code IntBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntBinaryOperator fromIntBinaryOperator(IntBinaryOperator operator) {
		return new CatchingIntBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingIntBinaryOperator implements OptionalIntBinaryOperator {
		private final IntBinaryOperator operator;
		@Override public OptionalInt apply(int left, int right) {
			try {
				return OptionalInt.of(operator.applyAsInt(left, right));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@code LongBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongBinaryOperator((l, r) -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code LongBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongBinaryOperator fromLongBinaryOperator(LongBinaryOperator operator) {
		return new CatchingLongBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingLongBinaryOperator implements OptionalLongBinaryOperator {
		private final LongBinaryOperator operator;
		@Override public OptionalLong apply(long left, long right) {
			try {
				return OptionalLong.of(operator.applyAsLong(left, right));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalLong.empty();
			}
		}
	}
	/**
	 * Wraps {@code DoubleBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code operator} is caught too.
	 * Wrapper then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda)}
	 * 
	 * @param operator
	 *            the {@code DoubleBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleBinaryOperator fromDoubleBinaryOperator(DoubleBinaryOperator operator) {
		return new CatchingDoubleBinaryOperator(operator);
	}
	@RequiredArgsConstructor private final class CatchingDoubleBinaryOperator implements OptionalDoubleBinaryOperator {
		private final DoubleBinaryOperator operator;
		@Override public OptionalDouble apply(double left, double right) {
			try {
				return OptionalDouble.of(operator.applyAsDouble(left, right));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalDouble.empty();
			}
		}
	}
	/**
	 * Runs {@code Runnable} in a try-catch block.
	 * <p>
	 * If {@code runnable} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code runnable} is caught too.
	 * This method then completes silently unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().run(() -> my_throwing_lambda)}
	 * 
	 * @param runnable
	 *            the {@code Runnable} to run, usually a lambda
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final void run(Runnable runnable) {
		try {
			runnable.run();
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
		}
	}
	/**
	 * Runs {@code Supplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * This method then returns empty {@code Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code Supplier} to run, usually a lambda
	 * @return an {@code Optional} carrying {@code supplier} result or an empty {@code Optional} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Optional<T> get(Supplier<T> supplier) {
		try {
			return Optional.ofNullable(supplier.get());
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
			return Optional.empty();
		}
	}
	/**
	 * Runs {@code IntSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * This method then returns empty {@code OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsInt(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code IntSupplier} to run, usually a lambda
	 * @return an {@code OptionalInt} carrying {@code supplier} result or an empty {@code OptionalInt} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalInt getAsInt(IntSupplier supplier) {
		try {
			return OptionalInt.of(supplier.getAsInt());
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
			return OptionalInt.empty();
		}
	}
	/**
	 * Runs {@code LongSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * This method then returns empty {@code OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsLong(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code LongSupplier} to run, usually a lambda
	 * @return an {@code OptionalLong} carrying {@code supplier} result or an empty {@code OptionalLong} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLong getAsLong(LongSupplier supplier) {
		try {
			return OptionalLong.of(supplier.getAsLong());
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
			return OptionalLong.empty();
		}
	}
	/**
	 * Runs {@code DoubleSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * This method then returns empty {@code OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsDouble(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code DoubleSupplier} to run, usually a lambda
	 * @return an {@code OptionalDouble} carrying {@code supplier} result or an empty {@code OptionalDouble} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDouble getAsDouble(DoubleSupplier supplier) {
		try {
			return OptionalDouble.of(supplier.getAsDouble());
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
			return OptionalDouble.empty();
		}
	}
	/**
	 * Runs {@code BooleanSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)}.
	 * {@code NullPointerException} from null {@code supplier} is caught too.
	 * This method then returns empty {@code OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsBoolean(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@code BooleanSupplier} to run, usually a lambda
	 * @return an {@code OptionalBoolean} carrying {@code supplier} result or an empty {@code OptionalBoolean} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalBoolean getAsBoolean(BooleanSupplier supplier) {
		try {
			return OptionalBoolean.of(supplier.getAsBoolean());
		} catch (Throwable exception) {
			if (!handle(exception))
				throw exception;
			return OptionalBoolean.empty();
		}
	}
}
