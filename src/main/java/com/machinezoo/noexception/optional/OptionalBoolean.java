// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

/**
 * A container object which may or may not contain a {@code boolean} value.
 * If value is present, {@link #isPresent()} will return {@code true} and {@link #getAsBoolean()} will return the value.
 * <p>
 * Additional methods that depend on the presence or absence of a contained value are provided,
 * such as {@link #orElse(boolean)} (return a default value if value is not present)
 * and {@link #ifPresent(IntConsumer)} (execute a block of code if the value is present).
 */
public final class OptionalBoolean {
	private static final OptionalBoolean empty = new OptionalBoolean(false, false);
	private static final OptionalBoolean optionalFalse = new OptionalBoolean(true, false);
	private static final OptionalBoolean optionalTrue = new OptionalBoolean(true, true);
	private final boolean present;
	private final boolean value;
	private OptionalBoolean(boolean present, boolean value) {
		this.present = present;
		this.value = value;
	}
	/**
	 * Returns an empty {@code OptionalBoolean} instance.
	 * No value is present for this {@code OptionalBoolean}.
	 * There is only one instance of empty {@code OptionalBoolean}.
	 * 
	 * @return an empty {@code OptionalBoolean}
	 */
	public static OptionalBoolean empty() {
		return empty;
	}
	/**
	 * Returns an {@code OptionalBoolean} with the specified value present.
	 * There are only two global instances of {@code OptionalBoolean} with value present.
	 * This method just returns one of them.
	 * 
	 * @param value
	 *            - the value to be present
	 * @return an {@code OptionalBoolean} with the value present
	 */
	public static OptionalBoolean of(boolean value) {
		return value ? optionalTrue : optionalFalse;
	}
	/**
	 * Returns {@code true} if there is a value present, otherwise {@code false}.
	 * 
	 * @return {@code true} if there is a value present, otherwise {@code false}
	 */
	public boolean isPresent() {
		return present;
	}
	/**
	 * If a value is present in this {@code OptionalBoolean}, returns the value, otherwise throws {@code NoSuchElementException}.
	 * 
	 * @return the value held by this {@code OptionalBoolean}
	 * @throws NoSuchElementException
	 *             if there is no value present
	 * @see #isPresent()
	 */
	public boolean getAsBoolean() {
		if (!present)
			throw new NoSuchElementException();
		return value;
	}
	/**
	 * Return the value if present, otherwise return {@code other}.
	 * 
	 * @param other
	 *            the value to be returned if there is no value present
	 * @return the value, if present, otherwise {@code other}
	 */
	public boolean orElse(boolean other) {
		return present ? value : other;
	}
	/**
	 * Return the value if present, otherwise invoke {@code other} and return the result of that invocation.
	 * 
	 * @param other
	 *            a {@code BooleanSupplier} whose result is returned if no value is present
	 * @return the value if present, otherwise the result of {@code other.getAsBoolean()}
	 * @throws NullPointerException
	 *             if value is not present and {@code other} is {@code null}
	 */
	public boolean orElseGet(BooleanSupplier other) {
		return present ? value : other.getAsBoolean();
	}
	/**
	 * Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
	 * A method reference to the exception constructor with an empty argument list can be used as the supplier.
	 * For example, {@code IllegalStateException::new}.
	 * 
	 * @param <X>
	 *            type of the exception to be thrown
	 * @param exceptionSupplier
	 *            the supplier which will return the exception to be thrown
	 * @return the present value
	 * @throws X
	 *             if there is no value present
	 * @throws NullPointerException
	 *             if no value is present and {@code exceptionSupplier} is {@code null}
	 */
	public <X extends Throwable> boolean orElseThrow(Supplier<X> exceptionSupplier) throws X {
		if (!present)
			throw exceptionSupplier.get();
		return value;
	}
	/**
	 * Have the specified consumer accept the value if a value is present, otherwise do nothing.
	 * There is no {@code BooleanConsumer} in JDK.
	 * Value of this {@code OptionalBoolean} is therefore widened to int and passed to {@code IntConsumer}.
	 * Value of {@code true} is widened to {@code 1}, {@code false} to {@code 0}.
	 * 
	 * @param consumer
	 *            block to be executed if a value is present
	 * @throws NullPointerException
	 *             if value is present and {@code consumer} is null
	 */
	public void ifPresent(IntConsumer consumer) {
		if (present)
			consumer.accept(value ? 1 : 0);
	}
	/**
	 * Indicates whether some other object is "equal to" this {@code OptionalBoolean}.
	 * The other object is considered equal if it is also an {@code OptionalBoolean}
	 * and both instances have no value present or the present values are the same.
	 * 
	 * @param obj
	 *            an object to be tested for equality
	 * @return {@code true} if the other object is "equal to" this object, otherwise {@code false}
	 * @see #hashCode()
	 */
	@Override public boolean equals(Object obj) {
		return obj == this;
	}
	/**
	 * Returns the hash code value of the present value, if any, or zero if no value is present.
	 * 
	 * @return hash code value of the present value of 0 if no value is present
	 * @see #equals(Object)
	 * @see Boolean#hashCode()
	 */
	@Override public int hashCode() {
		return !present ? 0 : Boolean.hashCode(value);
	}
	/**
	 * Returns string representation of this instance suitable for debugging.
	 * In the current implementation, one of the strings {@code empty}, {@code true}, or {@code false} is returned.
	 * 
	 * @return the string representation of this instance
	 */
	@Override public String toString() {
		return !present ? "empty" : value ? "true" : "false";
	}
}
