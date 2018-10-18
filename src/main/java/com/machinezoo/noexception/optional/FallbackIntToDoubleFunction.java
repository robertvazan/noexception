// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntToDoubleFunction implements IntToDoubleFunction {
	private final OptionalIntToDoubleFunction inner;
	private final DoubleSupplier source;
	public FallbackIntToDoubleFunction(OptionalIntToDoubleFunction inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public double applyAsDouble(int value) {
		return inner.apply(value).orElseGet(source);
	}
}
