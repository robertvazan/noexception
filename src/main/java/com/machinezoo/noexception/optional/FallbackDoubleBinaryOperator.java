// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleBinaryOperator implements DoubleBinaryOperator {
	private final OptionalDoubleBinaryOperator inner;
	private final DoubleSupplier source;
	public FallbackDoubleBinaryOperator(OptionalDoubleBinaryOperator inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public double applyAsDouble(double left, double right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
