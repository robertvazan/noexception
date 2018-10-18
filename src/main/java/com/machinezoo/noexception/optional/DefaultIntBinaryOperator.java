// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultIntBinaryOperator implements IntBinaryOperator {
	private final OptionalIntBinaryOperator inner;
	private final int result;
	public DefaultIntBinaryOperator(OptionalIntBinaryOperator inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int applyAsInt(int left, int right) {
		return inner.apply(left, right).orElse(result);
	}
}
