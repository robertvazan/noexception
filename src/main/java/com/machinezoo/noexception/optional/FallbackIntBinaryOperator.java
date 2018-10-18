// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntBinaryOperator implements IntBinaryOperator {
	private final OptionalIntBinaryOperator inner;
	private final IntSupplier source;
	public FallbackIntBinaryOperator(OptionalIntBinaryOperator inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(int left, int right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
