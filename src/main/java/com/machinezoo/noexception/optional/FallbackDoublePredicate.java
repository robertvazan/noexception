// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoublePredicate implements DoublePredicate {
	private final OptionalDoublePredicate inner;
	private final BooleanSupplier source;
	public FallbackDoublePredicate(OptionalDoublePredicate inner, BooleanSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public boolean test(double value) {
		return inner.test(value).orElseGet(source);
	}
}
