// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import java.util.*;
import java.util.function.*;
import com.machinezoo.closeablescope.CloseableScope;
import com.machinezoo.noexception.throwing.*;

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
 * and then applying exception handling policy with {@link ExceptionHandler}.
 * <p>
 * Combined usage: {@code Exceptions.silence().get(Exceptions.sneak().supplier(() -> my_throwing_lambda)).orElse(fallback)}
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
 * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
 * @see #handle(Exception)
 * @see Exceptions
 * @see ExceptionHandler
 * @see ExceptionFilter
 */
public abstract class CheckedExceptionHandler {
    /**
     * Converts checked exception into an unchecked one. This method must be defined in a derived class.
     * Several implementations are provided by methods on {@link Exceptions} class.
     * All other methods of the {@code CheckedExceptionHandler} call this method, but it can be also called directly.
     * <p>
     * This method represents reusable catch block that handles all checked exceptions in the same way.
     * When invoked, it must somehow convert the checked exception into an unchecked one, usually by wrapping it.
     * Caller is then expected to throw the returned exception.
     * There can be special cases like {@link Exceptions#sneak()}, which don't return at all.
     * <p>
     * Callers should not pass in {@link RuntimeException} or other unchecked exceptions.
     * This method might erroneously wrap such exceptions as if they are checked exceptions.
     * Methods of this class never pass unchecked exceptions to this method.
     * 
     * @param exception
     *            checked exception to convert
     * @return converted unchecked exception
     * @throws NullPointerException
     *             if {@code exception} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public abstract RuntimeException handle(Exception exception);
    /**
     * Initializes new {@code CheckedExceptionHandler}.
     */
    protected CheckedExceptionHandler() {
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingRunnable}.
     * <p>
     * If {@code runnable} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code runnable} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingRunnable(Exceptions.sneak().runnable(() -> my_throwing_lambda))}
     * 
     * @param runnable
     *            the {@link ThrowingRunnable} to be converted, usually a lambda
     * @return converted {@link Runnable} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final Runnable runnable(ThrowingRunnable runnable) {
        return new CheckedRunnable(runnable);
    }
    private final class CheckedRunnable implements Runnable {
        private final ThrowingRunnable runnable;
        CheckedRunnable(ThrowingRunnable runnable) {
            this.runnable = runnable;
        }
        @Override
        public void run() {
            try {
                runnable.run();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code supplier} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingSupplier(Exceptions.sneak().supplier(() -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Supplier}
     * @param supplier
     *            the {@link ThrowingSupplier} to be converted, usually a lambda
     * @return converted {@link Supplier} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> Supplier<T> supplier(ThrowingSupplier<T> supplier) {
        return new CheckedSupplier<T>(supplier);
    }
    private final class CheckedSupplier<T> implements Supplier<T> {
        private final ThrowingSupplier<T> supplier;
        CheckedSupplier(ThrowingSupplier<T> supplier) {
            this.supplier = supplier;
        }
        @Override
        public T get() {
            try {
                return supplier.get();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code supplier} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntSupplier(Exceptions.sneak().fromIntSupplier(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingIntSupplier} to be converted, usually a lambda
     * @return converted {@link IntSupplier} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntSupplier fromIntSupplier(ThrowingIntSupplier supplier) {
        return new CheckedIntSupplier(supplier);
    }
    private final class CheckedIntSupplier implements IntSupplier {
        private final ThrowingIntSupplier supplier;
        CheckedIntSupplier(ThrowingIntSupplier supplier) {
            this.supplier = supplier;
        }
        @Override
        public int getAsInt() {
            try {
                return supplier.getAsInt();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code supplier} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongSupplier(Exceptions.sneak().fromLongSupplier(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingLongSupplier} to be converted, usually a lambda
     * @return converted {@link LongSupplier} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongSupplier fromLongSupplier(ThrowingLongSupplier supplier) {
        return new CheckedLongSupplier(supplier);
    }
    private final class CheckedLongSupplier implements LongSupplier {
        private final ThrowingLongSupplier supplier;
        CheckedLongSupplier(ThrowingLongSupplier supplier) {
            this.supplier = supplier;
        }
        @Override
        public long getAsLong() {
            try {
                return supplier.getAsLong();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code supplier} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleSupplier(Exceptions.sneak().fromDoubleSupplier(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingDoubleSupplier} to be converted, usually a lambda
     * @return converted {@link DoubleSupplier} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleSupplier fromDoubleSupplier(ThrowingDoubleSupplier supplier) {
        return new CheckedDoubleSupplier(supplier);
    }
    private final class CheckedDoubleSupplier implements DoubleSupplier {
        private final ThrowingDoubleSupplier supplier;
        CheckedDoubleSupplier(ThrowingDoubleSupplier supplier) {
            this.supplier = supplier;
        }
        @Override
        public double getAsDouble() {
            try {
                return supplier.getAsDouble();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingBooleanSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code supplier} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingBooleanSupplier(Exceptions.sneak().fromBooleanSupplier(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingBooleanSupplier} to be converted, usually a lambda
     * @return converted {@link BooleanSupplier} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final BooleanSupplier fromBooleanSupplier(ThrowingBooleanSupplier supplier) {
        return new CheckedBooleanSupplier(supplier);
    }
    private final class CheckedBooleanSupplier implements BooleanSupplier {
        private final ThrowingBooleanSupplier supplier;
        CheckedBooleanSupplier(ThrowingBooleanSupplier supplier) {
            this.supplier = supplier;
        }
        @Override
        public boolean getAsBoolean() {
            try {
                return supplier.getAsBoolean();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingConsumer(Exceptions.sneak().consumer(t -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Consumer}
     * @param consumer
     *            the {@link ThrowingConsumer} to be converted, usually a lambda
     * @return converted {@link Consumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> Consumer<T> consumer(ThrowingConsumer<T> consumer) {
        return new CheckedConsumer<T>(consumer);
    }
    private final class CheckedConsumer<T> implements Consumer<T> {
        private final ThrowingConsumer<T> consumer;
        CheckedConsumer(ThrowingConsumer<T> consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(T t) {
            try {
                consumer.accept(t);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntConsumer(Exceptions.sneak().fromIntConsumer(v -> my_throwing_lambda))}
     * 
     * @param consumer
     *            the {@link ThrowingIntConsumer} to be converted, usually a lambda
     * @return converted {@link IntConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntConsumer fromIntConsumer(ThrowingIntConsumer consumer) {
        return new CheckedIntConsumer(consumer);
    }
    private final class CheckedIntConsumer implements IntConsumer {
        private final ThrowingIntConsumer consumer;
        CheckedIntConsumer(ThrowingIntConsumer consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(int value) {
            try {
                consumer.accept(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongConsumer(Exceptions.sneak().fromLongConsumer(v -> my_throwing_lambda))}
     * 
     * @param consumer
     *            the {@link ThrowingLongConsumer} to be converted, usually a lambda
     * @return converted {@link LongConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongConsumer fromLongConsumer(ThrowingLongConsumer consumer) {
        return new CheckedLongConsumer(consumer);
    }
    private final class CheckedLongConsumer implements LongConsumer {
        private final ThrowingLongConsumer consumer;
        CheckedLongConsumer(ThrowingLongConsumer consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(long value) {
            try {
                consumer.accept(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleConsumer(Exceptions.sneak().fromDoubleConsumer(v -> my_throwing_lambda))}
     * 
     * @param consumer
     *            the {@link ThrowingDoubleConsumer} to be converted, usually a lambda
     * @return converted {@link DoubleConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleConsumer fromDoubleConsumer(ThrowingDoubleConsumer consumer) {
        return new CheckedDoubleConsumer(consumer);
    }
    private final class CheckedDoubleConsumer implements DoubleConsumer {
        private final ThrowingDoubleConsumer consumer;
        CheckedDoubleConsumer(ThrowingDoubleConsumer consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(double value) {
            try {
                consumer.accept(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingBiConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingBiConsumer(Exceptions.sneak().fromBiConsumer((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link BiConsumer}
     * @param <U>
     *            see {@link BiConsumer}
     * @param consumer
     *            the {@link ThrowingBiConsumer} to be converted, usually a lambda
     * @return converted {@link BiConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U> BiConsumer<T, U> fromBiConsumer(ThrowingBiConsumer<T, U> consumer) {
        return new CheckedBiConsumer<T, U>(consumer);
    }
    private final class CheckedBiConsumer<T, U> implements BiConsumer<T, U> {
        private final ThrowingBiConsumer<T, U> consumer;
        CheckedBiConsumer(ThrowingBiConsumer<T, U> consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(T t, U u) {
            try {
                consumer.accept(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingObjIntConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingObjIntConsumer(Exceptions.sneak().fromObjIntConsumer((t, v) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ObjIntConsumer}
     * @param consumer
     *            the {@link ThrowingObjIntConsumer} to be converted, usually a lambda
     * @return converted {@link ObjIntConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ObjIntConsumer<T> fromObjIntConsumer(ThrowingObjIntConsumer<T> consumer) {
        return new CheckedObjIntConsumer<T>(consumer);
    }
    private final class CheckedObjIntConsumer<T> implements ObjIntConsumer<T> {
        private final ThrowingObjIntConsumer<T> consumer;
        CheckedObjIntConsumer(ThrowingObjIntConsumer<T> consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(T t, int value) {
            try {
                consumer.accept(t, value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingObjLongConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingObjLongConsumer(Exceptions.sneak().fromObjLongConsumer((t, v) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ObjLongConsumer}
     * @param consumer
     *            the {@link ThrowingObjLongConsumer} to be converted, usually a lambda
     * @return converted {@link ObjLongConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ObjLongConsumer<T> fromObjLongConsumer(ThrowingObjLongConsumer<T> consumer) {
        return new CheckedObjLongConsumer<T>(consumer);
    }
    private final class CheckedObjLongConsumer<T> implements ObjLongConsumer<T> {
        private final ThrowingObjLongConsumer<T> consumer;
        CheckedObjLongConsumer(ThrowingObjLongConsumer<T> consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(T t, long value) {
            try {
                consumer.accept(t, value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingObjDoubleConsumer}.
     * <p>
     * If {@code consumer} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code consumer} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingObjDoubleConsumer(Exceptions.sneak().fromObjDoubleConsumer((t, v) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ObjDoubleConsumer}
     * @param consumer
     *            the {@link ThrowingObjDoubleConsumer} to be converted, usually a lambda
     * @return converted {@link ObjDoubleConsumer} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ObjDoubleConsumer<T> fromObjDoubleConsumer(ThrowingObjDoubleConsumer<T> consumer) {
        return new CheckedObjDoubleConsumer<T>(consumer);
    }
    private final class CheckedObjDoubleConsumer<T> implements ObjDoubleConsumer<T> {
        private final ThrowingObjDoubleConsumer<T> consumer;
        CheckedObjDoubleConsumer(ThrowingObjDoubleConsumer<T> consumer) {
            this.consumer = consumer;
        }
        @Override
        public void accept(T t, double value) {
            try {
                consumer.accept(t, value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingPredicate}.
     * <p>
     * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code predicate} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingPredicate(Exceptions.sneak().predicate(t -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Predicate}
     * @param predicate
     *            the {@link ThrowingPredicate} to be converted, usually a lambda
     * @return converted {@link Predicate} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> Predicate<T> predicate(ThrowingPredicate<T> predicate) {
        return new CheckedPredicate<T>(predicate);
    }
    private final class CheckedPredicate<T> implements Predicate<T> {
        private final ThrowingPredicate<T> predicate;
        CheckedPredicate(ThrowingPredicate<T> predicate) {
            this.predicate = predicate;
        }
        @Override
        public boolean test(T t) {
            try {
                return predicate.test(t);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntPredicate}.
     * <p>
     * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code predicate} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntPredicate(Exceptions.sneak().fromIntPredicate(v -> my_throwing_lambda))}
     * 
     * @param predicate
     *            the {@link ThrowingIntPredicate} to be converted, usually a lambda
     * @return converted {@link IntPredicate} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntPredicate fromIntPredicate(ThrowingIntPredicate predicate) {
        return new CheckedIntPredicate(predicate);
    }
    private final class CheckedIntPredicate implements IntPredicate {
        private final ThrowingIntPredicate predicate;
        CheckedIntPredicate(ThrowingIntPredicate predicate) {
            this.predicate = predicate;
        }
        @Override
        public boolean test(int value) {
            try {
                return predicate.test(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongPredicate}.
     * <p>
     * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code predicate} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongPredicate(Exceptions.sneak().fromLongPredicate(v -> my_throwing_lambda))}
     * 
     * @param predicate
     *            the {@link ThrowingLongPredicate} to be converted, usually a lambda
     * @return converted {@link LongPredicate} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongPredicate fromLongPredicate(ThrowingLongPredicate predicate) {
        return new CheckedLongPredicate(predicate);
    }
    private final class CheckedLongPredicate implements LongPredicate {
        private final ThrowingLongPredicate predicate;
        CheckedLongPredicate(ThrowingLongPredicate predicate) {
            this.predicate = predicate;
        }
        @Override
        public boolean test(long value) {
            try {
                return predicate.test(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoublePredicate}.
     * <p>
     * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code predicate} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoublePredicate(Exceptions.sneak().fromDoublePredicate(v -> my_throwing_lambda))}
     * 
     * @param predicate
     *            the {@link ThrowingDoublePredicate} to be converted, usually a lambda
     * @return converted {@link DoublePredicate} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoublePredicate fromDoublePredicate(ThrowingDoublePredicate predicate) {
        return new CheckedDoublePredicate(predicate);
    }
    private final class CheckedDoublePredicate implements DoublePredicate {
        private final ThrowingDoublePredicate predicate;
        CheckedDoublePredicate(ThrowingDoublePredicate predicate) {
            this.predicate = predicate;
        }
        @Override
        public boolean test(double value) {
            try {
                return predicate.test(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingBiPredicate}.
     * <p>
     * If {@code predicate} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code predicate} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingBiPredicate(Exceptions.sneak().fromBiPredicate((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link BiPredicate}
     * @param <U>
     *            see {@link BiPredicate}
     * @param predicate
     *            the {@link ThrowingBiPredicate} to be converted, usually a lambda
     * @return converted {@link BiPredicate} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U> BiPredicate<T, U> fromBiPredicate(ThrowingBiPredicate<T, U> predicate) {
        return new CheckedBiPredicate<T, U>(predicate);
    }
    private final class CheckedBiPredicate<T, U> implements BiPredicate<T, U> {
        private final ThrowingBiPredicate<T, U> predicate;
        CheckedBiPredicate(ThrowingBiPredicate<T, U> predicate) {
            this.predicate = predicate;
        }
        @Override
        public boolean test(T t, U u) {
            try {
                return predicate.test(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingFunction(Exceptions.sneak().function(t -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Function}
     * @param <R>
     *            see {@link Function}
     * @param function
     *            the {@link ThrowingFunction} to be converted, usually a lambda
     * @return converted {@link Function} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, R> Function<T, R> function(ThrowingFunction<T, R> function) {
        return new CheckedFunction<T, R>(function);
    }
    private final class CheckedFunction<T, R> implements Function<T, R> {
        private final ThrowingFunction<T, R> function;
        CheckedFunction(ThrowingFunction<T, R> function) {
            this.function = function;
        }
        @Override
        public R apply(T t) {
            try {
                return function.apply(t);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToIntFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToIntFunction(Exceptions.sneak().fromToIntFunction(v -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToIntFunction}
     * @param function
     *            the {@link ThrowingToIntFunction} to be converted, usually a lambda
     * @return converted {@link ToIntFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ToIntFunction<T> fromToIntFunction(ThrowingToIntFunction<T> function) {
        return new CheckedToIntFunction<T>(function);
    }
    private final class CheckedToIntFunction<T> implements ToIntFunction<T> {
        private final ThrowingToIntFunction<T> function;
        CheckedToIntFunction(ThrowingToIntFunction<T> function) {
            this.function = function;
        }
        @Override
        public int applyAsInt(T value) {
            try {
                return function.applyAsInt(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntFunction(Exceptions.sneak().fromIntFunction(v -> my_throwing_lambda))}
     * 
     * @param <R>
     *            see {@link IntFunction}
     * @param function
     *            the {@link ThrowingIntFunction} to be converted, usually a lambda
     * @return converted {@link IntFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <R> IntFunction<R> fromIntFunction(ThrowingIntFunction<R> function) {
        return new CheckedIntFunction<R>(function);
    }
    private final class CheckedIntFunction<R> implements IntFunction<R> {
        private final ThrowingIntFunction<R> function;
        CheckedIntFunction(ThrowingIntFunction<R> function) {
            this.function = function;
        }
        @Override
        public R apply(int value) {
            try {
                return function.apply(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntToLongFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntToLongFunction(Exceptions.sneak().fromIntToLongFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingIntToLongFunction} to be converted, usually a lambda
     * @return converted {@link IntToLongFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntToLongFunction fromIntToLongFunction(ThrowingIntToLongFunction function) {
        return new CheckedIntToLongFunction(function);
    }
    private final class CheckedIntToLongFunction implements IntToLongFunction {
        private final ThrowingIntToLongFunction function;
        CheckedIntToLongFunction(ThrowingIntToLongFunction function) {
            this.function = function;
        }
        @Override
        public long applyAsLong(int value) {
            try {
                return function.applyAsLong(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntToDoubleFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntToDoubleFunction(Exceptions.sneak().fromIntToDoubleFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingIntToDoubleFunction} to be converted, usually a lambda
     * @return converted {@link IntToDoubleFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntToDoubleFunction fromIntToDoubleFunction(ThrowingIntToDoubleFunction function) {
        return new CheckedIntToDoubleFunction(function);
    }
    private final class CheckedIntToDoubleFunction implements IntToDoubleFunction {
        private final ThrowingIntToDoubleFunction function;
        CheckedIntToDoubleFunction(ThrowingIntToDoubleFunction function) {
            this.function = function;
        }
        @Override
        public double applyAsDouble(int value) {
            try {
                return function.applyAsDouble(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToLongFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToLongFunction(Exceptions.sneak().fromToLongFunction(v -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToLongFunction}
     * @param function
     *            the {@link ThrowingToLongFunction} to be converted, usually a lambda
     * @return converted {@link ToLongFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ToLongFunction<T> fromToLongFunction(ThrowingToLongFunction<T> function) {
        return new CheckedToLongFunction<T>(function);
    }
    private final class CheckedToLongFunction<T> implements ToLongFunction<T> {
        private final ThrowingToLongFunction<T> function;
        CheckedToLongFunction(ThrowingToLongFunction<T> function) {
            this.function = function;
        }
        @Override
        public long applyAsLong(T value) {
            try {
                return function.applyAsLong(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongFunction(Exceptions.sneak().fromLongFunction(v -> my_throwing_lambda))}
     * 
     * @param <R>
     *            see {@link LongFunction}
     * @param function
     *            the {@link ThrowingLongFunction} to be converted, usually a lambda
     * @return converted {@link LongFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <R> LongFunction<R> fromLongFunction(ThrowingLongFunction<R> function) {
        return new CheckedLongFunction<R>(function);
    }
    private final class CheckedLongFunction<R> implements LongFunction<R> {
        private final ThrowingLongFunction<R> function;
        CheckedLongFunction(ThrowingLongFunction<R> function) {
            this.function = function;
        }
        @Override
        public R apply(long value) {
            try {
                return function.apply(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongToIntFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongToIntFunction(Exceptions.sneak().fromLongToIntFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingLongToIntFunction} to be converted, usually a lambda
     * @return converted {@link LongToIntFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongToIntFunction fromLongToIntFunction(ThrowingLongToIntFunction function) {
        return new CheckedLongToIntFunction(function);
    }
    private final class CheckedLongToIntFunction implements LongToIntFunction {
        private final ThrowingLongToIntFunction function;
        CheckedLongToIntFunction(ThrowingLongToIntFunction function) {
            this.function = function;
        }
        @Override
        public int applyAsInt(long value) {
            try {
                return function.applyAsInt(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongToDoubleFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongToDoubleFunction(Exceptions.sneak().fromLongToDoubleFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingLongToDoubleFunction} to be converted, usually a lambda
     * @return converted {@link LongToDoubleFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongToDoubleFunction fromLongToDoubleFunction(ThrowingLongToDoubleFunction function) {
        return new CheckedLongToDoubleFunction(function);
    }
    private final class CheckedLongToDoubleFunction implements LongToDoubleFunction {
        private final ThrowingLongToDoubleFunction function;
        CheckedLongToDoubleFunction(ThrowingLongToDoubleFunction function) {
            this.function = function;
        }
        @Override
        public double applyAsDouble(long value) {
            try {
                return function.applyAsDouble(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToDoubleFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToDoubleFunction(Exceptions.sneak().fromToDoubleFunction(v -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToDoubleFunction}
     * @param function
     *            the {@link ThrowingToDoubleFunction} to be converted, usually a lambda
     * @return converted {@link ToDoubleFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> ToDoubleFunction<T> fromToDoubleFunction(ThrowingToDoubleFunction<T> function) {
        return new CheckedToDoubleFunction<T>(function);
    }
    private final class CheckedToDoubleFunction<T> implements ToDoubleFunction<T> {
        private final ThrowingToDoubleFunction<T> function;
        CheckedToDoubleFunction(ThrowingToDoubleFunction<T> function) {
            this.function = function;
        }
        @Override
        public double applyAsDouble(T value) {
            try {
                return function.applyAsDouble(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleFunction(Exceptions.sneak().fromDoubleFunction(v -> my_throwing_lambda))}
     * 
     * @param <R>
     *            see {@link DoubleFunction}
     * @param function
     *            the {@link ThrowingDoubleFunction} to be converted, usually a lambda
     * @return converted {@link DoubleFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <R> DoubleFunction<R> fromDoubleFunction(ThrowingDoubleFunction<R> function) {
        return new CheckedDoubleFunction<R>(function);
    }
    private final class CheckedDoubleFunction<R> implements DoubleFunction<R> {
        private final ThrowingDoubleFunction<R> function;
        CheckedDoubleFunction(ThrowingDoubleFunction<R> function) {
            this.function = function;
        }
        @Override
        public R apply(double value) {
            try {
                return function.apply(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleToIntFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleToIntFunction(Exceptions.sneak().fromDoubleToIntFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingDoubleToIntFunction} to be converted, usually a lambda
     * @return converted {@link DoubleToIntFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleToIntFunction fromDoubleToIntFunction(ThrowingDoubleToIntFunction function) {
        return new CheckedDoubleToIntFunction(function);
    }
    private final class CheckedDoubleToIntFunction implements DoubleToIntFunction {
        private final ThrowingDoubleToIntFunction function;
        CheckedDoubleToIntFunction(ThrowingDoubleToIntFunction function) {
            this.function = function;
        }
        @Override
        public int applyAsInt(double value) {
            try {
                return function.applyAsInt(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleToLongFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleToLongFunction(Exceptions.sneak().fromDoubleToLongFunction(v -> my_throwing_lambda))}
     * 
     * @param function
     *            the {@link ThrowingDoubleToLongFunction} to be converted, usually a lambda
     * @return converted {@link DoubleToLongFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleToLongFunction fromDoubleToLongFunction(ThrowingDoubleToLongFunction function) {
        return new CheckedDoubleToLongFunction(function);
    }
    private final class CheckedDoubleToLongFunction implements DoubleToLongFunction {
        private final ThrowingDoubleToLongFunction function;
        CheckedDoubleToLongFunction(ThrowingDoubleToLongFunction function) {
            this.function = function;
        }
        @Override
        public long applyAsLong(double value) {
            try {
                return function.applyAsLong(value);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingUnaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingUnaryOperator(Exceptions.sneak().fromUnaryOperator(o -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link UnaryOperator}
     * @param operator
     *            the {@link ThrowingUnaryOperator} to be converted, usually a lambda
     * @return converted {@link UnaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> UnaryOperator<T> fromUnaryOperator(ThrowingUnaryOperator<T> operator) {
        return new CheckedUnaryOperator<T>(operator);
    }
    private final class CheckedUnaryOperator<T> implements UnaryOperator<T> {
        private final ThrowingUnaryOperator<T> operator;
        CheckedUnaryOperator(ThrowingUnaryOperator<T> operator) {
            this.operator = operator;
        }
        @Override
        public T apply(T operand) {
            try {
                return operator.apply(operand);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntUnaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntUnaryOperator(Exceptions.sneak().fromIntUnaryOperator(o -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingIntUnaryOperator} to be converted, usually a lambda
     * @return converted {@link IntUnaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntUnaryOperator fromIntUnaryOperator(ThrowingIntUnaryOperator operator) {
        return new CheckedIntUnaryOperator(operator);
    }
    private final class CheckedIntUnaryOperator implements IntUnaryOperator {
        private final ThrowingIntUnaryOperator operator;
        CheckedIntUnaryOperator(ThrowingIntUnaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public int applyAsInt(int operand) {
            try {
                return operator.applyAsInt(operand);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongUnaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongUnaryOperator(Exceptions.sneak().fromLongUnaryOperator(o -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingLongUnaryOperator} to be converted, usually a lambda
     * @return converted {@link LongUnaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongUnaryOperator fromLongUnaryOperator(ThrowingLongUnaryOperator operator) {
        return new CheckedLongUnaryOperator(operator);
    }
    private final class CheckedLongUnaryOperator implements LongUnaryOperator {
        private final ThrowingLongUnaryOperator operator;
        CheckedLongUnaryOperator(ThrowingLongUnaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public long applyAsLong(long operand) {
            try {
                return operator.applyAsLong(operand);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleUnaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleUnaryOperator(Exceptions.sneak().fromDoubleUnaryOperator(o -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingDoubleUnaryOperator} to be converted, usually a lambda
     * @return converted {@link DoubleUnaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleUnaryOperator fromDoubleUnaryOperator(ThrowingDoubleUnaryOperator operator) {
        return new CheckedDoubleUnaryOperator(operator);
    }
    private final class CheckedDoubleUnaryOperator implements DoubleUnaryOperator {
        private final ThrowingDoubleUnaryOperator operator;
        CheckedDoubleUnaryOperator(ThrowingDoubleUnaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public double applyAsDouble(double operand) {
            try {
                return operator.applyAsDouble(operand);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingBiFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingBiFunction(Exceptions.sneak().fromBiFunction((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link BiFunction}
     * @param <U>
     *            see {@link BiFunction}
     * @param <R>
     *            see {@link BiFunction}
     * @param function
     *            the {@link ThrowingBiFunction} to be converted, usually a lambda
     * @return converted {@link BiFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U, R> BiFunction<T, U, R> fromBiFunction(ThrowingBiFunction<T, U, R> function) {
        return new CheckedBiFunction<T, U, R>(function);
    }
    private final class CheckedBiFunction<T, U, R> implements BiFunction<T, U, R> {
        private final ThrowingBiFunction<T, U, R> function;
        CheckedBiFunction(ThrowingBiFunction<T, U, R> function) {
            this.function = function;
        }
        @Override
        public R apply(T t, U u) {
            try {
                return function.apply(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToIntBiFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToIntBiFunction(Exceptions.sneak().fromToIntBiFunction((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToIntBiFunction}
     * @param <U>
     *            see {@link ToIntBiFunction}
     * @param function
     *            the {@link ThrowingToIntBiFunction} to be converted, usually a lambda
     * @return converted {@link ToIntBiFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U> ToIntBiFunction<T, U> fromToIntBiFunction(ThrowingToIntBiFunction<T, U> function) {
        return new CheckedToIntBiFunction<T, U>(function);
    }
    private final class CheckedToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
        private final ThrowingToIntBiFunction<T, U> function;
        CheckedToIntBiFunction(ThrowingToIntBiFunction<T, U> function) {
            this.function = function;
        }
        @Override
        public int applyAsInt(T t, U u) {
            try {
                return function.applyAsInt(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToLongBiFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToLongBiFunction(Exceptions.sneak().fromToLongBiFunction((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToLongBiFunction}
     * @param <U>
     *            see {@link ToLongBiFunction}
     * @param function
     *            the {@link ThrowingToLongBiFunction} to be converted, usually a lambda
     * @return converted {@link ToLongBiFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U> ToLongBiFunction<T, U> fromToLongBiFunction(ThrowingToLongBiFunction<T, U> function) {
        return new CheckedToLongBiFunction<T, U>(function);
    }
    private final class CheckedToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
        private final ThrowingToLongBiFunction<T, U> function;
        CheckedToLongBiFunction(ThrowingToLongBiFunction<T, U> function) {
            this.function = function;
        }
        @Override
        public long applyAsLong(T t, U u) {
            try {
                return function.applyAsLong(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingToDoubleBiFunction}.
     * <p>
     * If {@code function} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code function} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingToDoubleBiFunction(Exceptions.sneak().fromToDoubleBiFunction((t, u) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link ToDoubleBiFunction}
     * @param <U>
     *            see {@link ToDoubleBiFunction}
     * @param function
     *            the {@link ThrowingToDoubleBiFunction} to be converted, usually a lambda
     * @return converted {@link ToDoubleBiFunction} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T, U> ToDoubleBiFunction<T, U> fromToDoubleBiFunction(ThrowingToDoubleBiFunction<T, U> function) {
        return new CheckedToDoubleBiFunction<T, U>(function);
    }
    private final class CheckedToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
        private final ThrowingToDoubleBiFunction<T, U> function;
        CheckedToDoubleBiFunction(ThrowingToDoubleBiFunction<T, U> function) {
            this.function = function;
        }
        @Override
        public double applyAsDouble(T t, U u) {
            try {
                return function.applyAsDouble(t, u);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingBinaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingBinaryOperator(Exceptions.sneak().fromBinaryOperator((l, r) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link BinaryOperator}
     * @param operator
     *            the {@link ThrowingBinaryOperator} to be converted, usually a lambda
     * @return converted {@link BinaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> BinaryOperator<T> fromBinaryOperator(ThrowingBinaryOperator<T> operator) {
        return new CheckedBinaryOperator<T>(operator);
    }
    private final class CheckedBinaryOperator<T> implements BinaryOperator<T> {
        private final ThrowingBinaryOperator<T> operator;
        CheckedBinaryOperator(ThrowingBinaryOperator<T> operator) {
            this.operator = operator;
        }
        @Override
        public T apply(T left, T right) {
            try {
                return operator.apply(left, right);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingIntBinaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingIntBinaryOperator(Exceptions.sneak().fromIntBinaryOperator((l, r) -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingIntBinaryOperator} to be converted, usually a lambda
     * @return converted {@link IntBinaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final IntBinaryOperator fromIntBinaryOperator(ThrowingIntBinaryOperator operator) {
        return new CheckedIntBinaryOperator(operator);
    }
    private final class CheckedIntBinaryOperator implements IntBinaryOperator {
        private final ThrowingIntBinaryOperator operator;
        CheckedIntBinaryOperator(ThrowingIntBinaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public int applyAsInt(int left, int right) {
            try {
                return operator.applyAsInt(left, right);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingLongBinaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingLongBinaryOperator(Exceptions.sneak().fromLongBinaryOperator((l, r) -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingLongBinaryOperator} to be converted, usually a lambda
     * @return converted {@link LongBinaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final LongBinaryOperator fromLongBinaryOperator(ThrowingLongBinaryOperator operator) {
        return new CheckedLongBinaryOperator(operator);
    }
    private final class CheckedLongBinaryOperator implements LongBinaryOperator {
        private final ThrowingLongBinaryOperator operator;
        CheckedLongBinaryOperator(ThrowingLongBinaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public long applyAsLong(long left, long right) {
            try {
                return operator.applyAsLong(left, right);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingDoubleBinaryOperator}.
     * <p>
     * If {@code operator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code operator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingDoubleBinaryOperator(Exceptions.sneak().fromDoubleBinaryOperator((l, r) -> my_throwing_lambda))}
     * 
     * @param operator
     *            the {@link ThrowingDoubleBinaryOperator} to be converted, usually a lambda
     * @return converted {@link DoubleBinaryOperator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final DoubleBinaryOperator fromDoubleBinaryOperator(ThrowingDoubleBinaryOperator operator) {
        return new CheckedDoubleBinaryOperator(operator);
    }
    private final class CheckedDoubleBinaryOperator implements DoubleBinaryOperator {
        private final ThrowingDoubleBinaryOperator operator;
        CheckedDoubleBinaryOperator(ThrowingDoubleBinaryOperator operator) {
            this.operator = operator;
        }
        @Override
        public double applyAsDouble(double left, double right) {
            try {
                return operator.applyAsDouble(left, right);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link ThrowingComparator}.
     * <p>
     * If {@code comparator} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code comparator} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code methodTakingComparator(Exceptions.sneak().comparator((l, r) -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Comparator}
     * @param comparator
     *            the {@link ThrowingComparator} to be converted, usually a lambda
     * @return converted {@link Comparator} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> Comparator<T> comparator(ThrowingComparator<T> comparator) {
        return new CheckedComparator<T>(comparator);
    }
    private final class CheckedComparator<T> implements Comparator<T> {
        private final ThrowingComparator<T> comparator;
        CheckedComparator(ThrowingComparator<T> comparator) {
            this.comparator = comparator;
        }
        @Override
        public int compare(T left, T right) {
            try {
                return comparator.compare(left, right);
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Removes checked exceptions from method signature of {@link AutoCloseable}.
     * <p>
     * If {@code closeable} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * Null {@code closeable} is silently wrapped and causes {@link NullPointerException} when executed.
     * <p>
     * Typical usage: {@code try (var scope = Exceptions.sneak().closeable(openSomething()))}
     * 
     * @param closeable
     *            the {@link AutoCloseable} to be converted
     * @return converted {@link CloseableScope} free of checked exceptions
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final CloseableScope closeable(AutoCloseable closeable) {
        return new CheckedCloseableScope(closeable);
    }
    private final class CheckedCloseableScope implements CloseableScope {
        private final AutoCloseable closeable;
        CheckedCloseableScope(AutoCloseable closeable) {
            this.closeable = closeable;
        }
        @Override
        public void close() {
            try {
                closeable.close();
            } catch (RuntimeException exception) {
                throw exception;
            } catch (Exception exception) {
                throw handle(exception);
            } catch (Throwable exception) {
                throw SneakingHandler.sneak(exception);
            }
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingRunnable}.
     * <p>
     * If {@code runnable} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().run(() -> my_throwing_lambda))}
     * 
     * @param runnable
     *            the {@link ThrowingRunnable} to run, usually a lambda
     * @throws NullPointerException
     *             if {@code runnable} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final void run(ThrowingRunnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().get(() -> my_throwing_lambda))}
     * 
     * @param <T>
     *            see {@link Supplier}
     * @param supplier
     *            the {@link ThrowingSupplier} to run, usually a lambda
     * @return value returned from {@code supplier}
     * @throws NullPointerException
     *             if {@code supplier} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final <T> T get(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingIntSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().getAsInt(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingIntSupplier} to run, usually a lambda
     * @return value returned from {@code supplier}
     * @throws NullPointerException
     *             if {@code supplier} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final int getAsInt(ThrowingIntSupplier supplier) {
        try {
            return supplier.getAsInt();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingLongSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().getAsLong(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingLongSupplier} to run, usually a lambda
     * @return value returned from {@code supplier}
     * @throws NullPointerException
     *             if {@code supplier} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final long getAsLong(ThrowingLongSupplier supplier) {
        try {
            return supplier.getAsLong();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingDoubleSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().getAsDouble(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingDoubleSupplier} to run, usually a lambda
     * @return value returned from {@code supplier}
     * @throws NullPointerException
     *             if {@code supplier} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final double getAsDouble(ThrowingDoubleSupplier supplier) {
        try {
            return supplier.getAsDouble();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
    /**
     * Filters out checked exceptions while running {@link ThrowingBooleanSupplier}.
     * <p>
     * If {@code supplier} throws a checked exception, the exception is caught and passed to {@link #handle(Exception)},
     * which usually converts it to an unchecked exception, which is then thrown by this method.
     * <p>
     * Typical usage: {@code Exceptions.sneak().getAsBoolean(() -> my_throwing_lambda))}
     * 
     * @param supplier
     *            the {@link ThrowingBooleanSupplier} to run, usually a lambda
     * @return value returned from {@code supplier}
     * @throws NullPointerException
     *             if {@code supplier} is {@code null}
     * @see <a href="https://noexception.machinezoo.com/">Tutorial</a>
     * @see Exceptions
     */
    public final boolean getAsBoolean(ThrowingBooleanSupplier supplier) {
        try {
            return supplier.getAsBoolean();
        } catch (RuntimeException exception) {
            throw exception;
        } catch (Exception exception) {
            throw handle(exception);
        } catch (Throwable exception) {
            throw SneakingHandler.sneak(exception);
        }
    }
}
