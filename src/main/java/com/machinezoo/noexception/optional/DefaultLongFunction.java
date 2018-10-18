// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongFunction<R> implements LongFunction<R> {
	private final OptionalLongFunction<R> inner;
	private final R result;
	public DefaultLongFunction(OptionalLongFunction<R> inner, R result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public R apply(long value) {
		return inner.apply(value).orElse(result);
	}
}
