// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongSupplier implements LongSupplier {
	private final OptionalLongSupplier inner;
	private final long result;
	public DefaultLongSupplier(OptionalLongSupplier inner, long result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public long getAsLong() {
		return inner.get().orElse(result);
	}
}
