// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackSupplier<T> implements Supplier<T> {
	private final OptionalSupplier<T> inner;
	private final Supplier<T> source;
	public FallbackSupplier(OptionalSupplier<T> inner, Supplier<T> source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public T get() {
		return inner.get().orElseGet(source);
	}
}
