// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoublePredicate implements DoublePredicate {
	private final OptionalDoublePredicate inner;
	private final boolean result;
	public DefaultDoublePredicate(OptionalDoublePredicate inner, boolean result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public boolean test(double value) {
		return inner.test(value).orElse(result);
	}
}
