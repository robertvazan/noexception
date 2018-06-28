// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongToDoubleFunction implements LongToDoubleFunction {
	private final OptionalLongToDoubleFunction inner;
	private final double result;
	public DefaultLongToDoubleFunction(OptionalLongToDoubleFunction inner, double result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public double applyAsDouble(long value) {
		return inner.apply(value).orElse(result);
	}
}
