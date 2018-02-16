// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleToIntFunction implements DoubleToIntFunction {
	private final OptionalDoubleToIntFunction inner;
	private final IntSupplier source;
	public FallbackDoubleToIntFunction(OptionalDoubleToIntFunction inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(double value) {
		return inner.apply(value).orElseGet(source);
	}
}
