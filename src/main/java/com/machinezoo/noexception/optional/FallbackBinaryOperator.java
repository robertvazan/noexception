// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackBinaryOperator<T> implements BinaryOperator<T> {
	private final OptionalBinaryOperator<T> inner;
	private final Supplier<T> source;
	public FallbackBinaryOperator(OptionalBinaryOperator<T> inner, Supplier<T> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override
	public T apply(T left, T right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
