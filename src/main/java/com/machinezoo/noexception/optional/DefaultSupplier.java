// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultSupplier<T> implements Supplier<T> {
	private final OptionalSupplier<T> inner;
	private final T result;
	public DefaultSupplier(OptionalSupplier<T> inner, T result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public T get() {
		return inner.get().orElse(result);
	}
}
