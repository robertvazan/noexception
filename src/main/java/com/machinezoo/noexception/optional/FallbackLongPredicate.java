// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongPredicate implements LongPredicate {
	private final OptionalLongPredicate inner;
	private final BooleanSupplier source;
	public FallbackLongPredicate(OptionalLongPredicate inner, BooleanSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public boolean test(long value) {
		return inner.test(value).orElseGet(source);
	}
}
