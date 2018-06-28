// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleFunction<R> implements DoubleFunction<R> {
	private final OptionalDoubleFunction<R> inner;
	private final R result;
	public DefaultDoubleFunction(OptionalDoubleFunction<R> inner, R result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public R apply(double value) {
		return inner.apply(value).orElse(result);
	}
}
