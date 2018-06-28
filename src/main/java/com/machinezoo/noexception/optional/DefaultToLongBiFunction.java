// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
	private final OptionalToLongBiFunction<T, U> inner;
	private final long result;
	public DefaultToLongBiFunction(OptionalToLongBiFunction<T, U> inner, long result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public long applyAsLong(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
