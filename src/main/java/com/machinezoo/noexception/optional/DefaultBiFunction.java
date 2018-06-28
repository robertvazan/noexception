// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultBiFunction<T, U, R> implements BiFunction<T, U, R> {
	private final OptionalBiFunction<T, U, R> inner;
	private final R result;
	public DefaultBiFunction(OptionalBiFunction<T, U, R> inner, R result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public R apply(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
