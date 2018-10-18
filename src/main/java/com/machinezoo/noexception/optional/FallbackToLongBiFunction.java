// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
	private final OptionalToLongBiFunction<T, U> inner;
	private final LongSupplier source;
	public FallbackToLongBiFunction(OptionalToLongBiFunction<T, U> inner, LongSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public long applyAsLong(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
