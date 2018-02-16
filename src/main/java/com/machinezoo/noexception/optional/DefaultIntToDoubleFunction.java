// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultIntToDoubleFunction implements IntToDoubleFunction {
	private final OptionalIntToDoubleFunction inner;
	private final double result;
	public DefaultIntToDoubleFunction(OptionalIntToDoubleFunction inner, double result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public double applyAsDouble(int value) {
		return inner.apply(value).orElse(result);
	}
}
