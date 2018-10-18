// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongSupplier implements LongSupplier {
	private final OptionalLongSupplier inner;
	private final LongSupplier source;
	public FallbackLongSupplier(OptionalLongSupplier inner, LongSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public long getAsLong() {
		return inner.get().orElseGet(source);
	}
}
