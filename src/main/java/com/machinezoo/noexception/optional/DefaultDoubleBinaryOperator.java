// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleBinaryOperator implements DoubleBinaryOperator {
	private final OptionalDoubleBinaryOperator inner;
	private final double result;
	public DefaultDoubleBinaryOperator(OptionalDoubleBinaryOperator inner, double result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public double applyAsDouble(double left, double right) {
		return inner.apply(left, right).orElse(result);
	}
}
