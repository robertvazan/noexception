// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultIntUnaryOperator implements IntUnaryOperator {
	private final OptionalIntUnaryOperator inner;
	private final int result;
	public DefaultIntUnaryOperator(OptionalIntUnaryOperator inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int applyAsInt(int operand) {
		return inner.apply(operand).orElse(result);
	}
}
