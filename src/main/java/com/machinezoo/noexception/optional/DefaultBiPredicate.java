// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultBiPredicate<T, U> implements BiPredicate<T, U> {
	private final OptionalBiPredicate<T, U> inner;
	private final boolean result;
	public DefaultBiPredicate(OptionalBiPredicate<T, U> inner, boolean result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public boolean test(T t, U u) {
		return inner.test(t, u).orElse(result);
	}
}
