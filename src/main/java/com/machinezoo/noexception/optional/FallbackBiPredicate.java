// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackBiPredicate<T, U> implements BiPredicate<T, U> {
	private final OptionalBiPredicate<T, U> inner;
	private final BooleanSupplier source;
	public FallbackBiPredicate(OptionalBiPredicate<T, U> inner, BooleanSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public boolean test(T t, U u) {
		return inner.test(t, u).orElseGet(source);
	}
}
