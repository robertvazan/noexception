// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
	private final OptionalToIntBiFunction<T, U> inner;
	private final IntSupplier source;
	public FallbackToIntBiFunction(OptionalToIntBiFunction<T, U> inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
