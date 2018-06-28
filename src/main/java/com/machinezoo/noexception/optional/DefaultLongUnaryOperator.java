// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongUnaryOperator implements LongUnaryOperator {
	private final OptionalLongUnaryOperator inner;
	private final long result;
	public DefaultLongUnaryOperator(OptionalLongUnaryOperator inner, long result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public long applyAsLong(long operand) {
		return inner.apply(operand).orElse(result);
	}
}
