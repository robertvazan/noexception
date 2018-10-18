// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToIntFunction<T> implements ToIntFunction<T> {
	private final OptionalToIntFunction<T> inner;
	private final IntSupplier source;
	public FallbackToIntFunction(OptionalToIntFunction<T> inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(T value) {
		return inner.apply(value).orElseGet(source);
	}
}
