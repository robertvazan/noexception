// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToDoubleFunction<T> implements ToDoubleFunction<T> {
	private final OptionalToDoubleFunction<T> inner;
	private final DoubleSupplier source;
	public FallbackToDoubleFunction(OptionalToDoubleFunction<T> inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public double applyAsDouble(T value) {
		return inner.apply(value).orElseGet(source);
	}
}
