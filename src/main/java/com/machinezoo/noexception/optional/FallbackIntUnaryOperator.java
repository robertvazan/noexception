// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntUnaryOperator implements IntUnaryOperator {
	private final OptionalIntUnaryOperator inner;
	private final IntSupplier source;
	public FallbackIntUnaryOperator(OptionalIntUnaryOperator inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(int operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
