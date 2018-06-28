// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleToLongFunction implements DoubleToLongFunction {
	private final OptionalDoubleToLongFunction inner;
	private final LongSupplier source;
	public FallbackDoubleToLongFunction(OptionalDoubleToLongFunction inner, LongSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public long applyAsLong(double value) {
		return inner.apply(value).orElseGet(source);
	}
}
