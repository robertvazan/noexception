// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleFunction<R> implements DoubleFunction<R> {
	private final OptionalDoubleFunction<R> inner;
	private final Supplier<R> source;
	public FallbackDoubleFunction(OptionalDoubleFunction<R> inner, Supplier<R> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public R apply(double value) {
		return inner.apply(value).orElseGet(source);
	}
}
