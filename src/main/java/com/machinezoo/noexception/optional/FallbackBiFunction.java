// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackBiFunction<T, U, R> implements BiFunction<T, U, R> {
	private final OptionalBiFunction<T, U, R> inner;
	private final Supplier<R> source;
	public FallbackBiFunction(OptionalBiFunction<T, U, R> inner, Supplier<R> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public R apply(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
