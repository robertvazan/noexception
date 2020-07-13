// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongFunction<R> implements LongFunction<R> {
	private final OptionalLongFunction<R> inner;
	private final Supplier<R> source;
	public FallbackLongFunction(OptionalLongFunction<R> inner, Supplier<R> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public R apply(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
