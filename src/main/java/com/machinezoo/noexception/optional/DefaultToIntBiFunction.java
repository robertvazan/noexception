// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
	private final OptionalToIntBiFunction<T, U> inner;
	private final int result;
	public DefaultToIntBiFunction(OptionalToIntBiFunction<T, U> inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override
	public int applyAsInt(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
