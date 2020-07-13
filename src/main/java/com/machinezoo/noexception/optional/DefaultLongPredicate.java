// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongPredicate implements LongPredicate {
	private final OptionalLongPredicate inner;
	private final boolean result;
	public DefaultLongPredicate(OptionalLongPredicate inner, boolean result) {
		this.inner = inner;
		this.result = result;
	}
	@Override
	public boolean test(long value) {
		return inner.test(value).orElse(result);
	}
}
