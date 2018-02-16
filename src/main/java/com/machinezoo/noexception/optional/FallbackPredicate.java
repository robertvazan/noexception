// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackPredicate<T> implements Predicate<T> {
	private final OptionalPredicate<T> inner;
	private final BooleanSupplier source;
	public FallbackPredicate(OptionalPredicate<T> inner, BooleanSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public boolean test(T t) {
		return inner.test(t).orElseGet(source);
	}
}
