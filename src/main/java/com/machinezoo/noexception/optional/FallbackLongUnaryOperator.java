// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongUnaryOperator implements LongUnaryOperator {
	private final OptionalLongUnaryOperator inner;
	private final LongSupplier source;
	public FallbackLongUnaryOperator(OptionalLongUnaryOperator inner, LongSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public long applyAsLong(long operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
