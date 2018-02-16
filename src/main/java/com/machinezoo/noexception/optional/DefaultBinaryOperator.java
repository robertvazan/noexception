// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultBinaryOperator<T> implements BinaryOperator<T> {
	private final OptionalBinaryOperator<T> inner;
	private final T result;
	public DefaultBinaryOperator(OptionalBinaryOperator<T> inner, T result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public T apply(T left, T right) {
		return inner.apply(left, right).orElse(result);
	}
}
