// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongToDoubleFunction implements LongToDoubleFunction {
	private final OptionalLongToDoubleFunction inner;
	private final DoubleSupplier source;
	public FallbackLongToDoubleFunction(OptionalLongToDoubleFunction inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public double applyAsDouble(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
