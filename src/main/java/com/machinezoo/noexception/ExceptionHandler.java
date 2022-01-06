// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import com.machinezoo.noexception.optional.*;
import com.machinezoo.stagean.*;

/**
 * Represents exception handling policy.
 * Methods of this class apply the exception policy to functional interfaces (usually lambdas) by wrapping them in a try-catch block.
 * Method {@link #handle(Throwable)} defines the exception handling policy when implemented in derived class.
 * See <a href="https://noexception.machinezoo.com/">noexception tutorial</a>.
 * <p>
 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda).orElse(fallback)}
 * <p>
 * All wrapping methods surround the functional interface with a try-catch block.
 * If the functional interface throws, the exception is caught and passed to {@link #handle(Throwable)},
 * which applies exception handling policy (log, silence, ignore, custom).
 * {@link NullPointerException} from {@code null} functional interface is caught too.
 * Unless {@link #handle(Throwable)} requests a rethrow, void functional interfaces complete normally
 * while non-void functional interfaces return empty {@link Optional}.
 * <p>
 * Wrapping methods for all standard functional interfaces are provided.
 * Simple interfaces have short method names like {@link #runnable(Runnable)} or {@link #supplier(Supplier)}.
 * Interfaces with longer names have methods that follow {@code fromX} naming pattern, for example {@link #fromUnaryOperator(UnaryOperator)}.
 * Parameterless functional interfaces can be called directly by methods {@link #run(Runnable)}, {@link #get(Supplier)},
 * and the various {@code getAsX} variants.
 * <p>
 * All non-void wrappers conform to some {@code OptionalX} functional interface, for example {@link OptionalSupplier},
 * that is identical to its non-optional variant from JDK except it returns {@link Optional} instead of raw value.
 * This {@link Optional} is empty in case of exception.
 * Callers can use {@link Optional#orElse(Object)} and {@link Optional#orElseGet(Supplier)} and their
 * equivalents on {@code OptionalX} interfaces to provide fallback values.
 * 
 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
 * @see #handle(Throwable)
 * @see Exceptions
 * @see OptionalSupplier
 * @see Optional
 * @see ExceptionFilter
 * @see CheckedExceptionHandler
 */
@ApiIssue("GitHub issue #3: Add wrapped(), sneaked(), and checked(handler) that return CombinedExceptionHandler (checked -> optional).")
/*
 * CheckedExceptionHandler cannot have the same methods, because it must always handle checked exceptions.
 * ExceptionFilter does not need similar methods, because it is really only used to implement passing().
 * Rigid fluent syntax: LoggedExceptions.log().except(exclusions).run(task).
 * Or with checked exceptions: LoggedExceptions.log().except(exclusions).sneaked().run(task).
 * Once implemented, it can be used to improve ReactiveExceptions.
 */
@ApiIssue("Add only/except(Predicate<Throwable>), which return handler that rethrows selected exceptions without passing them to handle().")
public abstract class ExceptionHandler {
	/**
	 * Handles exception in a generic way. This method must be defined in a derived class.
	 * Several implementations are provided by methods on {@link Exceptions} class.
	 * All other methods of the {@code ExceptionHandler} call this method, but it can be also called directly.
	 * <p>
	 * This method represents reusable catch block that handles all exceptions in the same way.
	 * When invoked, it must somehow handle the provided exception, for example by logging it.
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
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public abstract boolean handle(Throwable exception);
	/**
	 * Initialize new {@code ExceptionHandler}.
	 */
	protected ExceptionHandler() {
	}
	/**
	 * Adds a pass-through modifier to this exception handler.
	 * If this exception handler performs an action (like logging) and then stops exception propagation,
	 * this method will return {@link ExceptionFilter} that performs the same action but additionally rethrows the exception.
	 * <p>
	 * Reusable exception handlers can be defined once as {@code ExceptionHandler} instances
	 * and then transformed into {@link ExceptionFilter} by this method when needed.
	 * <p>
	 * If method {@link #handle(Throwable)} throws, the returned {@link ExceptionFilter} will pass through that exception.
	 * It only rethrows the original exception if {@link #handle(Throwable)} returns normally (regardless of return value).
	 * <p>
	 * Typical usage: {@code Exceptions.log().passing().get(() -> my_throwing_lambda)}
	 *
	 * @return pass-through modification of this exception handler
	 */
	public ExceptionFilter passing() {
		return new PassingFilter(this);
	}
	/**
	 * Wraps {@link Runnable} in a try-catch block.
	 * <p>
	 * If {@code runnable} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code runnable} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().runnable(() -> my_throwing_lambda)}
	 * 
	 * @param runnable
	 *            the {@link Runnable} to wrap, usually a lambda
	 * @return wrapper that runs {@code runnable} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final Runnable runnable(Runnable runnable) {
		return new CatchingRunnable(runnable);
	}
	private final class CatchingRunnable implements Runnable {
		private final Runnable runnable;
		CatchingRunnable(Runnable runnable) {
			this.runnable = runnable;
		}
		@Override
		public void run() {
			try {
				runnable.run();
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link Supplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().supplier(() -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link Supplier}
	 * @param supplier
	 *            the {@link Supplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalSupplier<T> supplier(Supplier<T> supplier) {
		return new CatchingSupplier<T>(supplier);
	}
	private final class CatchingSupplier<T> implements OptionalSupplier<T> {
		private final Supplier<T> supplier;
		CatchingSupplier(Supplier<T> supplier) {
			this.supplier = supplier;
		}
		@Override
		public Optional<T> get() {
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
	 * Wraps {@link IntSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntSupplier(() -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param supplier
	 *            the {@link IntSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntSupplier fromIntSupplier(IntSupplier supplier) {
		return new CatchingIntSupplier(supplier);
	}
	private final class CatchingIntSupplier implements OptionalIntSupplier {
		private final IntSupplier supplier;
		CatchingIntSupplier(IntSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public OptionalInt get() {
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
	 * Wraps {@link LongSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongSupplier(() -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param supplier
	 *            the {@link LongSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongSupplier fromLongSupplier(LongSupplier supplier) {
		return new CatchingLongSupplier(supplier);
	}
	private final class CatchingLongSupplier implements OptionalLongSupplier {
		private final LongSupplier supplier;
		CatchingLongSupplier(LongSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public OptionalLong get() {
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
	 * Wraps {@link DoubleSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleSupplier(() -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param supplier
	 *            the {@link DoubleSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleSupplier fromDoubleSupplier(DoubleSupplier supplier) {
		return new CatchingDoubleSupplier(supplier);
	}
	private final class CatchingDoubleSupplier implements OptionalDoubleSupplier {
		private final DoubleSupplier supplier;
		CatchingDoubleSupplier(DoubleSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public OptionalDouble get() {
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
	 * Wraps {@link BooleanSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBooleanSupplier(() -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param supplier
	 *            the {@link BooleanSupplier} to wrap, usually a lambda
	 * @return wrapper that runs {@code supplier} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalBooleanSupplier fromBooleanSupplier(BooleanSupplier supplier) {
		return new CatchingBooleanSupplier(supplier);
	}
	private final class CatchingBooleanSupplier implements OptionalBooleanSupplier {
		private final BooleanSupplier supplier;
		CatchingBooleanSupplier(BooleanSupplier supplier) {
			this.supplier = supplier;
		}
		@Override
		public OptionalBoolean get() {
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
	 * Wraps {@link Consumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().consumer(t -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link Consumer}
	 * @param consumer
	 *            the {@link Consumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> Consumer<T> consumer(Consumer<T> consumer) {
		return new CatchingConsumer<T>(consumer);
	}
	private final class CatchingConsumer<T> implements Consumer<T> {
		private final Consumer<T> consumer;
		CatchingConsumer(Consumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t) {
			try {
				consumer.accept(t);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link IntConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@link IntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final IntConsumer fromIntConsumer(IntConsumer consumer) {
		return new CatchingIntConsumer(consumer);
	}
	private final class CatchingIntConsumer implements IntConsumer {
		private final IntConsumer consumer;
		CatchingIntConsumer(IntConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(int value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link LongConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@link LongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final LongConsumer fromLongConsumer(LongConsumer consumer) {
		return new CatchingLongConsumer(consumer);
	}
	private final class CatchingLongConsumer implements LongConsumer {
		private final LongConsumer consumer;
		CatchingLongConsumer(LongConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(long value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link DoubleConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleConsumer(v -> my_throwing_lambda)}
	 * 
	 * @param consumer
	 *            the {@link DoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final DoubleConsumer fromDoubleConsumer(DoubleConsumer consumer) {
		return new CatchingDoubleConsumer(consumer);
	}
	private final class CatchingDoubleConsumer implements DoubleConsumer {
		private final DoubleConsumer consumer;
		CatchingDoubleConsumer(DoubleConsumer consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(double value) {
			try {
				consumer.accept(value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link BiConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiConsumer((t, u) -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link BiConsumer}
	 * @param <U>
	 *            see {@link BiConsumer}
	 * @param consumer
	 *            the {@link BiConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> BiConsumer<T, U> fromBiConsumer(BiConsumer<T, U> consumer) {
		return new CatchingBiConsumer<T, U>(consumer);
	}
	private final class CatchingBiConsumer<T, U> implements BiConsumer<T, U> {
		private final BiConsumer<T, U> consumer;
		CatchingBiConsumer(BiConsumer<T, U> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, U u) {
			try {
				consumer.accept(t, u);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link ObjIntConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjIntConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link ObjIntConsumer}
	 * @param consumer
	 *            the {@link ObjIntConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjIntConsumer<T> fromObjIntConsumer(ObjIntConsumer<T> consumer) {
		return new CatchingObjIntConsumer<T>(consumer);
	}
	private final class CatchingObjIntConsumer<T> implements ObjIntConsumer<T> {
		private final ObjIntConsumer<T> consumer;
		CatchingObjIntConsumer(ObjIntConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, int value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link ObjLongConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjLongConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link ObjLongConsumer}
	 * @param consumer
	 *            the {@link ObjLongConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjLongConsumer<T> fromObjLongConsumer(ObjLongConsumer<T> consumer) {
		return new CatchingObjLongConsumer<T>(consumer);
	}
	private final class CatchingObjLongConsumer<T> implements ObjLongConsumer<T> {
		private final ObjLongConsumer<T> consumer;
		CatchingObjLongConsumer(ObjLongConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, long value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link ObjDoubleConsumer} in a try-catch block.
	 * <p>
	 * If {@code consumer} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code consumer} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromObjDoubleConsumer((t, v) -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link ObjDoubleConsumer}
	 * @param consumer
	 *            the {@link ObjDoubleConsumer} to wrap, usually a lambda
	 * @return wrapper that runs {@code consumer} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ObjDoubleConsumer<T> consumer) {
		return new CatchingObjDoubleConsumer<T>(consumer);
	}
	private final class CatchingObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
		private final ObjDoubleConsumer<T> consumer;
		CatchingObjDoubleConsumer(ObjDoubleConsumer<T> consumer) {
			this.consumer = consumer;
		}
		@Override
		public void accept(T t, double value) {
			try {
				consumer.accept(t, value);
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Wraps {@link Predicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().predicate(t -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link Predicate}
	 * @param predicate
	 *            the {@link Predicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalPredicate<T> predicate(Predicate<T> predicate) {
		return new CatchingPredicate<T>(predicate);
	}
	private final class CatchingPredicate<T> implements OptionalPredicate<T> {
		private final Predicate<T> predicate;
		CatchingPredicate(Predicate<T> predicate) {
			this.predicate = predicate;
		}
		@Override
		public OptionalBoolean test(T t) {
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
	 * Wraps {@link IntPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntPredicate(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param predicate
	 *            the {@link IntPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntPredicate fromIntPredicate(IntPredicate predicate) {
		return new CatchingIntPredicate(predicate);
	}
	private final class CatchingIntPredicate implements OptionalIntPredicate {
		private final IntPredicate predicate;
		CatchingIntPredicate(IntPredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public OptionalBoolean test(int value) {
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
	 * Wraps {@link LongPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongPredicate(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param predicate
	 *            the {@link LongPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongPredicate fromLongPredicate(LongPredicate predicate) {
		return new CatchingLongPredicate(predicate);
	}
	private final class CatchingLongPredicate implements OptionalLongPredicate {
		private final LongPredicate predicate;
		CatchingLongPredicate(LongPredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public OptionalBoolean test(long value) {
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
	 * Wraps {@link DoublePredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoublePredicate(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param predicate
	 *            the {@link DoublePredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoublePredicate fromDoublePredicate(DoublePredicate predicate) {
		return new CatchingDoublePredicate(predicate);
	}
	private final class CatchingDoublePredicate implements OptionalDoublePredicate {
		private final DoublePredicate predicate;
		CatchingDoublePredicate(DoublePredicate predicate) {
			this.predicate = predicate;
		}
		@Override
		public OptionalBoolean test(double value) {
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
	 * Wraps {@link BiPredicate} in a try-catch block.
	 * <p>
	 * If {@code predicate} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code predicate} is caught too.
	 * Wrapper then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiPredicate((t, u) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link BiPredicate}
	 * @param <U>
	 *            see {@link BiPredicate}
	 * @param predicate
	 *            the {@link BiPredicate} to wrap, usually a lambda
	 * @return wrapper that runs {@code predicate} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalBiPredicate<T, U> fromBiPredicate(BiPredicate<T, U> predicate) {
		return new CatchingBiPredicate<T, U>(predicate);
	}
	private final class CatchingBiPredicate<T, U> implements OptionalBiPredicate<T, U> {
		private final BiPredicate<T, U> predicate;
		CatchingBiPredicate(BiPredicate<T, U> predicate) {
			this.predicate = predicate;
		}
		@Override
		public OptionalBoolean test(T t, U u) {
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
	 * Wraps {@link Function} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().function(t -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link Function}
	 * @param <R>
	 *            see {@link Function}
	 * @param function
	 *            the {@link Function} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, R> OptionalFunction<T, R> function(Function<T, R> function) {
		return new CatchingFunction<T, R>(function);
	}
	private final class CatchingFunction<T, R> implements OptionalFunction<T, R> {
		private final Function<T, R> function;
		CatchingFunction(Function<T, R> function) {
			this.function = function;
		}
		@Override
		public Optional<R> apply(T t) {
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
	 * Wraps {@link ToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToIntFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToIntFunction}
	 * @param function
	 *            the {@link ToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToIntFunction<T> fromToIntFunction(ToIntFunction<T> function) {
		return new CatchingToIntFunction<T>(function);
	}
	private final class CatchingToIntFunction<T> implements OptionalToIntFunction<T> {
		private final ToIntFunction<T> function;
		CatchingToIntFunction(ToIntFunction<T> function) {
			this.function = function;
		}
		@Override
		public OptionalInt apply(T value) {
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
	 * Wraps {@link IntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <R>
	 *            see {@link IntFunction}
	 * @param function
	 *            the {@link IntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalIntFunction<R> fromIntFunction(IntFunction<R> function) {
		return new CatchingIntFunction<R>(function);
	}
	private final class CatchingIntFunction<R> implements OptionalIntFunction<R> {
		private final IntFunction<R> function;
		CatchingIntFunction(IntFunction<R> function) {
			this.function = function;
		}
		@Override
		public Optional<R> apply(int value) {
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
	 * Wraps {@link IntToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntToLongFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link IntToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntToLongFunction fromIntToLongFunction(IntToLongFunction function) {
		return new CatchingIntToLongFunction(function);
	}
	private final class CatchingIntToLongFunction implements OptionalIntToLongFunction {
		private final IntToLongFunction function;
		CatchingIntToLongFunction(IntToLongFunction function) {
			this.function = function;
		}
		@Override
		public OptionalLong apply(int value) {
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
	 * Wraps {@link IntToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntToDoubleFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link IntToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntToDoubleFunction fromIntToDoubleFunction(IntToDoubleFunction function) {
		return new CatchingIntToDoubleFunction(function);
	}
	private final class CatchingIntToDoubleFunction implements OptionalIntToDoubleFunction {
		private final IntToDoubleFunction function;
		CatchingIntToDoubleFunction(IntToDoubleFunction function) {
			this.function = function;
		}
		@Override
		public OptionalDouble apply(int value) {
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
	 * Wraps {@link ToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToLongFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToLongFunction}
	 * @param function
	 *            the {@link ToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToLongFunction<T> fromToLongFunction(ToLongFunction<T> function) {
		return new CatchingToLongFunction<T>(function);
	}
	private final class CatchingToLongFunction<T> implements OptionalToLongFunction<T> {
		private final ToLongFunction<T> function;
		CatchingToLongFunction(ToLongFunction<T> function) {
			this.function = function;
		}
		@Override
		public OptionalLong apply(T value) {
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
	 * Wraps {@link LongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <R>
	 *            see {@link LongFunction}
	 * @param function
	 *            the {@link LongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalLongFunction<R> fromLongFunction(LongFunction<R> function) {
		return new CatchingLongFunction<R>(function);
	}
	private final class CatchingLongFunction<R> implements OptionalLongFunction<R> {
		private final LongFunction<R> function;
		CatchingLongFunction(LongFunction<R> function) {
			this.function = function;
		}
		@Override
		public Optional<R> apply(long value) {
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
	 * Wraps {@link LongToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongToIntFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link LongToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongToIntFunction fromLongToIntFunction(LongToIntFunction function) {
		return new CatchingLongToIntFunction(function);
	}
	private final class CatchingLongToIntFunction implements OptionalLongToIntFunction {
		private final LongToIntFunction function;
		CatchingLongToIntFunction(LongToIntFunction function) {
			this.function = function;
		}
		@Override
		public OptionalInt apply(long value) {
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
	 * Wraps {@link LongToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongToDoubleFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link LongToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongToDoubleFunction fromLongToDoubleFunction(LongToDoubleFunction function) {
		return new CatchingLongToDoubleFunction(function);
	}
	private final class CatchingLongToDoubleFunction implements OptionalLongToDoubleFunction {
		private final LongToDoubleFunction function;
		CatchingLongToDoubleFunction(LongToDoubleFunction function) {
			this.function = function;
		}
		@Override
		public OptionalDouble apply(long value) {
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
	 * Wraps {@link ToDoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToDoubleFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToDoubleFunction}
	 * @param function
	 *            the {@link ToDoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalToDoubleFunction<T> fromToDoubleFunction(ToDoubleFunction<T> function) {
		return new CatchingToDoubleFunction<T>(function);
	}
	private final class CatchingToDoubleFunction<T> implements OptionalToDoubleFunction<T> {
		private final ToDoubleFunction<T> function;
		CatchingToDoubleFunction(ToDoubleFunction<T> function) {
			this.function = function;
		}
		@Override
		public OptionalDouble apply(T value) {
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
	 * Wraps {@link DoubleFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <R>
	 *            see {@link DoubleFunction}
	 * @param function
	 *            the {@link DoubleFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <R> OptionalDoubleFunction<R> fromDoubleFunction(DoubleFunction<R> function) {
		return new CatchingDoubleFunction<R>(function);
	}
	private final class CatchingDoubleFunction<R> implements OptionalDoubleFunction<R> {
		private final DoubleFunction<R> function;
		CatchingDoubleFunction(DoubleFunction<R> function) {
			this.function = function;
		}
		@Override
		public Optional<R> apply(double value) {
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
	 * Wraps {@link DoubleToIntFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleToIntFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link DoubleToIntFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleToIntFunction fromDoubleToIntFunction(DoubleToIntFunction function) {
		return new CatchingDoubleToIntFunction(function);
	}
	private final class CatchingDoubleToIntFunction implements OptionalDoubleToIntFunction {
		private final DoubleToIntFunction function;
		CatchingDoubleToIntFunction(DoubleToIntFunction function) {
			this.function = function;
		}
		@Override
		public OptionalInt apply(double value) {
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
	 * Wraps {@link DoubleToLongFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleToLongFunction(v -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param function
	 *            the {@link DoubleToLongFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleToLongFunction fromDoubleToLongFunction(DoubleToLongFunction function) {
		return new CatchingDoubleToLongFunction(function);
	}
	private final class CatchingDoubleToLongFunction implements OptionalDoubleToLongFunction {
		private final DoubleToLongFunction function;
		CatchingDoubleToLongFunction(DoubleToLongFunction function) {
			this.function = function;
		}
		@Override
		public OptionalLong apply(double value) {
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
	 * Wraps {@link UnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromUnaryOperator(o -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link UnaryOperator}
	 * @param operator
	 *            the {@link UnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalUnaryOperator<T> fromUnaryOperator(UnaryOperator<T> operator) {
		return new CatchingUnaryOperator<T>(operator);
	}
	private final class CatchingUnaryOperator<T> implements OptionalUnaryOperator<T> {
		private final UnaryOperator<T> operator;
		CatchingUnaryOperator(UnaryOperator<T> operator) {
			this.operator = operator;
		}
		@Override
		public Optional<T> apply(T operand) {
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
	 * Wraps {@link IntUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntUnaryOperator(o -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link IntUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntUnaryOperator fromIntUnaryOperator(IntUnaryOperator operator) {
		return new CatchingIntUnaryOperator(operator);
	}
	private final class CatchingIntUnaryOperator implements OptionalIntUnaryOperator {
		private final IntUnaryOperator operator;
		CatchingIntUnaryOperator(IntUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalInt apply(int operand) {
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
	 * Wraps {@link LongUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongUnaryOperator(o -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link LongUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongUnaryOperator fromLongUnaryOperator(LongUnaryOperator operator) {
		return new CatchingLongUnaryOperator(operator);
	}
	private final class CatchingLongUnaryOperator implements OptionalLongUnaryOperator {
		private final LongUnaryOperator operator;
		CatchingLongUnaryOperator(LongUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalLong apply(long operand) {
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
	 * Wraps {@link DoubleUnaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleUnaryOperator(o -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link DoubleUnaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleUnaryOperator fromDoubleUnaryOperator(DoubleUnaryOperator operator) {
		return new CatchingDoubleUnaryOperator(operator);
	}
	private final class CatchingDoubleUnaryOperator implements OptionalDoubleUnaryOperator {
		private final DoubleUnaryOperator operator;
		CatchingDoubleUnaryOperator(DoubleUnaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalDouble apply(double operand) {
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
	 * Wraps {@link BiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBiFunction((t, u) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link BiFunction}
	 * @param <U>
	 *            see {@link BiFunction}
	 * @param <R>
	 *            see {@link BiFunction}
	 * @param function
	 *            the {@link BiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U, R> OptionalBiFunction<T, U, R> fromBiFunction(BiFunction<T, U, R> function) {
		return new CatchingBiFunction<T, U, R>(function);
	}
	private final class CatchingBiFunction<T, U, R> implements OptionalBiFunction<T, U, R> {
		private final BiFunction<T, U, R> function;
		CatchingBiFunction(BiFunction<T, U, R> function) {
			this.function = function;
		}
		@Override
		public Optional<R> apply(T t, U u) {
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
	 * Wraps {@link ToIntBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToIntBiFunction((t, u) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToIntBiFunction}
	 * @param <U>
	 *            see {@link ToIntBiFunction}
	 * @param function
	 *            the {@link ToIntBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToIntBiFunction<T, U> fromToIntBiFunction(ToIntBiFunction<T, U> function) {
		return new CatchingToIntBiFunction<T, U>(function);
	}
	private final class CatchingToIntBiFunction<T, U> implements OptionalToIntBiFunction<T, U> {
		private final ToIntBiFunction<T, U> function;
		CatchingToIntBiFunction(ToIntBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public OptionalInt apply(T t, U u) {
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
	 * Wraps {@link ToLongBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToLongBiFunction((t, u) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToLongBiFunction}
	 * @param <U>
	 *            see {@link ToLongBiFunction}
	 * @param function
	 *            the {@link ToLongBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToLongBiFunction<T, U> fromToLongBiFunction(ToLongBiFunction<T, U> function) {
		return new CatchingToLongBiFunction<T, U>(function);
	}
	private final class CatchingToLongBiFunction<T, U> implements OptionalToLongBiFunction<T, U> {
		private final ToLongBiFunction<T, U> function;
		CatchingToLongBiFunction(ToLongBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public OptionalLong apply(T t, U u) {
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
	 * Wraps {@link ToDoubleBiFunction} in a try-catch block.
	 * <p>
	 * If {@code function} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code function} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromToDoubleBiFunction((t, u) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link ToDoubleBiFunction}
	 * @param <U>
	 *            see {@link ToDoubleBiFunction}
	 * @param function
	 *            the {@link ToDoubleBiFunction} to wrap, usually a lambda
	 * @return wrapper that runs {@code function} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T, U> OptionalToDoubleBiFunction<T, U> fromToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
		return new CatchingToDoubleBiFunction<T, U>(function);
	}
	private final class CatchingToDoubleBiFunction<T, U> implements OptionalToDoubleBiFunction<T, U> {
		private final ToDoubleBiFunction<T, U> function;
		CatchingToDoubleBiFunction(ToDoubleBiFunction<T, U> function) {
			this.function = function;
		}
		@Override
		public OptionalDouble apply(T t, U u) {
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
	 * Wraps {@link BinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromBinaryOperator((l, r) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link BinaryOperator}
	 * @param operator
	 *            the {@link BinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalBinaryOperator<T> fromBinaryOperator(BinaryOperator<T> operator) {
		return new CatchingBinaryOperator<T>(operator);
	}
	private final class CatchingBinaryOperator<T> implements OptionalBinaryOperator<T> {
		private final BinaryOperator<T> operator;
		CatchingBinaryOperator(BinaryOperator<T> operator) {
			this.operator = operator;
		}
		@Override
		public Optional<T> apply(T left, T right) {
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
	 * Wraps {@link IntBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromIntBinaryOperator((l, r) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link IntBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalIntBinaryOperator fromIntBinaryOperator(IntBinaryOperator operator) {
		return new CatchingIntBinaryOperator(operator);
	}
	private final class CatchingIntBinaryOperator implements OptionalIntBinaryOperator {
		private final IntBinaryOperator operator;
		CatchingIntBinaryOperator(IntBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalInt apply(int left, int right) {
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
	 * Wraps {@link LongBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromLongBinaryOperator((l, r) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link LongBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalLongBinaryOperator fromLongBinaryOperator(LongBinaryOperator operator) {
		return new CatchingLongBinaryOperator(operator);
	}
	private final class CatchingLongBinaryOperator implements OptionalLongBinaryOperator {
		private final LongBinaryOperator operator;
		CatchingLongBinaryOperator(LongBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalLong apply(long left, long right) {
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
	 * Wraps {@link DoubleBinaryOperator} in a try-catch block.
	 * <p>
	 * If {@code operator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code operator} is caught too.
	 * Wrapper then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param operator
	 *            the {@link DoubleBinaryOperator} to wrap, usually a lambda
	 * @return wrapper that runs {@code operator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final OptionalDoubleBinaryOperator fromDoubleBinaryOperator(DoubleBinaryOperator operator) {
		return new CatchingDoubleBinaryOperator(operator);
	}
	private final class CatchingDoubleBinaryOperator implements OptionalDoubleBinaryOperator {
		private final DoubleBinaryOperator operator;
		CatchingDoubleBinaryOperator(DoubleBinaryOperator operator) {
			this.operator = operator;
		}
		@Override
		public OptionalDouble apply(double left, double right) {
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
	 * Wraps {@link Comparator} in a try-catch block.
	 * <p>
	 * If {@code comparator} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code comparator} is caught too.
	 * Wrapper then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().comparator((l, r) -> my_throwing_lambda).orElse(fallback)}
	 * 
	 * @param <T>
	 *            see {@link Comparator}
	 * @param comparator
	 *            the {@link Comparator} to wrap, usually a lambda
	 * @return wrapper that runs {@code comparator} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final <T> OptionalComparator<T> comparator(Comparator<T> comparator) {
		return new CatchingComparator<T>(comparator);
	}
	private final class CatchingComparator<T> implements OptionalComparator<T> {
		private final Comparator<T> comparator;
		CatchingComparator(Comparator<T> comparator) {
			this.comparator = comparator;
		}
		@Override
		public OptionalInt compare(T left, T right) {
			try {
				return OptionalInt.of(comparator.compare(left, right));
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
				return OptionalInt.empty();
			}
		}
	}
	/**
	 * Wraps {@link CloseableScope} in a try-catch block.
	 * <p>
	 * If {@code closeable} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code closeable} is caught too.
	 * Wrapper then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code try (var scope = Exceptions.log().closeable(openSomething()))}
	 * 
	 * @param closeable
	 *            the {@link CloseableScope} to wrap
	 * @return wrapper that runs {@code closeable} in a try-catch block
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
	 * @see Exceptions
	 */
	public final CloseableScope closeable(CloseableScope closeable) {
		return new CatchingCloseableScope(closeable);
	}
	private final class CatchingCloseableScope implements CloseableScope {
		private final CloseableScope closeable;
		CatchingCloseableScope(CloseableScope closeable) {
			this.closeable = closeable;
		}
		@Override
		public void close() {
			try {
				closeable.close();
			} catch (Throwable exception) {
				if (!handle(exception))
					throw exception;
			}
		}
	}
	/**
	 * Runs {@link Runnable} in a try-catch block.
	 * <p>
	 * If {@code runnable} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code runnable} is caught too.
	 * This method then completes normally unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().run(() -> my_throwing_lambda)}
	 * 
	 * @param runnable
	 *            the {@link Runnable} to run, usually a lambda
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
	 * Runs {@link Supplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * This method then returns empty {@link Optional} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().get(() -> my_throwing_lambda)}
	 * 
	 * @param <T>
	 *            see {@link Supplier}
	 * @param supplier
	 *            the {@link Supplier} to run, usually a lambda
	 * @return an {@link Optional} carrying {@code supplier} result or an empty {@link Optional} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
	 * Runs {@link IntSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * This method then returns empty {@link OptionalInt} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsInt(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@link IntSupplier} to run, usually a lambda
	 * @return an {@link OptionalInt} carrying {@code supplier} result or an empty {@link OptionalInt} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
	 * Runs {@link LongSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * This method then returns empty {@link OptionalLong} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsLong(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@link LongSupplier} to run, usually a lambda
	 * @return an {@link OptionalLong} carrying {@code supplier} result or an empty {@link OptionalLong} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
	 * Runs {@link DoubleSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * This method then returns empty {@link OptionalDouble} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsDouble(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@link DoubleSupplier} to run, usually a lambda
	 * @return an {@link OptionalDouble} carrying {@code supplier} result or an empty {@link OptionalDouble} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
	 * Runs {@link BooleanSupplier} in a try-catch block.
	 * <p>
	 * If {@code supplier} throws, the exception is caught and passed to {@link #handle(Throwable)},
	 * which applies exception handling policy (log, silence, ignore, custom).
	 * {@link NullPointerException} from {@code null} {@code supplier} is caught too.
	 * This method then returns empty {@link OptionalBoolean} unless {@link #handle(Throwable)} requests a rethrow.
	 * <p>
	 * Typical usage: {@code Exceptions.log().getAsBoolean(() -> my_throwing_lambda)}
	 * 
	 * @param supplier
	 *            the {@link BooleanSupplier} to run, usually a lambda
	 * @return an {@link OptionalBoolean} carrying {@code supplier} result or an empty {@link OptionalBoolean} if exception was caught
	 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
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
