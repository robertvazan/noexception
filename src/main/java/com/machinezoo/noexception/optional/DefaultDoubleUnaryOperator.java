// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleUnaryOperator implements DoubleUnaryOperator {
	private final OptionalDoubleUnaryOperator inner;
	private final double result;
	public DefaultDoubleUnaryOperator(OptionalDoubleUnaryOperator inner, double result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public double applyAsDouble(double operand) {
		return inner.apply(operand).orElse(result);
	}
}
