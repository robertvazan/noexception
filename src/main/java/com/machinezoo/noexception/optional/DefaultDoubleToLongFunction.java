// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleToLongFunction implements DoubleToLongFunction {
	private final OptionalDoubleToLongFunction inner;
	private final long result;
	public DefaultDoubleToLongFunction(OptionalDoubleToLongFunction inner, long result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public long applyAsLong(double value) {
		return inner.apply(value).orElse(result);
	}
}
