// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
	private final OptionalToDoubleBiFunction<T, U> inner;
	private final DoubleSupplier source;
	public FallbackToDoubleBiFunction(OptionalToDoubleBiFunction<T, U> inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public double applyAsDouble(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
