// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToLongFunction<T> implements ToLongFunction<T> {
	private final OptionalToLongFunction<T> inner;
	private final LongSupplier source;
	public FallbackToLongFunction(OptionalToLongFunction<T> inner, LongSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public long applyAsLong(T value) {
		return inner.apply(value).orElseGet(source);
	}
}
