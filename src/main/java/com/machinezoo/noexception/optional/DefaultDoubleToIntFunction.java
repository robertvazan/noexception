// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleToIntFunction implements DoubleToIntFunction {
	private final OptionalDoubleToIntFunction inner;
	private final int result;
	public DefaultDoubleToIntFunction(OptionalDoubleToIntFunction inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int applyAsInt(double value) {
		return inner.apply(value).orElse(result);
	}
}
