// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleUnaryOperator implements DoubleUnaryOperator {
	private final OptionalDoubleUnaryOperator inner;
	private final DoubleSupplier source;
	public FallbackDoubleUnaryOperator(OptionalDoubleUnaryOperator inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public double applyAsDouble(double operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
