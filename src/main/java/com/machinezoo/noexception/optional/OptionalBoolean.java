// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

public class OptionalBoolean {
	private static final OptionalBoolean empty = new OptionalBoolean(false, false);
	private static final OptionalBoolean optionalFalse = new OptionalBoolean(true, false);
	private static final OptionalBoolean optionalTrue = new OptionalBoolean(true, true);
	private final boolean present;
	private final boolean value;
	private OptionalBoolean(boolean present, boolean value) {
		this.present = present;
		this.value = value;
	}
	public static OptionalBoolean empty() {
		return empty;
	}
	public static OptionalBoolean of(boolean value) {
		return value ? optionalTrue : optionalFalse;
	}
	public boolean isPresent() {
		return present;
	}
	public boolean getAsBoolean() {
		if (!present)
			throw new NoSuchElementException();
		return value;
	}
	public boolean orElse(boolean other) {
		return present ? value : other;
	}
	public boolean orElseGet(BooleanSupplier other) {
		return present ? value : other.getAsBoolean();
	}
	public <X extends Throwable> boolean orElseThrow(Supplier<X> exceptionSupplier) throws X {
		if (!present)
			throw exceptionSupplier.get();
		return value;
	}
	public void ifPresent(IntConsumer consumer) {
		if (present)
			consumer.accept(value ? 1 : 0);
	}
	@Override public boolean equals(Object obj) {
		return obj == this;
	}
	@Override public int hashCode() {
		return !present ? 531664329 : value ? 911124333 : 657981802;
	}
	@Override public String toString() {
		return !present ? "empty" : value ? "true" : "false";
	}
}
