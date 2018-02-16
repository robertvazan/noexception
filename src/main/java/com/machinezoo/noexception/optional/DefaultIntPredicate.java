// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultIntPredicate implements IntPredicate {
	private final OptionalIntPredicate inner;
	private final boolean result;
	public DefaultIntPredicate(OptionalIntPredicate inner, boolean result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public boolean test(int value) {
		return inner.test(value).orElse(result);
	}
}
