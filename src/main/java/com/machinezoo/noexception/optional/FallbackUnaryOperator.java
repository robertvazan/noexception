// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackUnaryOperator<T> implements UnaryOperator<T> {
	private final OptionalUnaryOperator<T> inner;
	private final Supplier<T> source;
	public FallbackUnaryOperator(OptionalUnaryOperator<T> inner, Supplier<T> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public T apply(T operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
