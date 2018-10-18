// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackIntSupplier implements IntSupplier {
	private final OptionalIntSupplier inner;
	private final IntSupplier source;
	public FallbackIntSupplier(OptionalIntSupplier inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int getAsInt() {
		return inner.get().orElseGet(source);
	}
}
