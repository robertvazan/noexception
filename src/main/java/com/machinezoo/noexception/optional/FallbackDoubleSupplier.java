// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackDoubleSupplier implements DoubleSupplier {
	private final OptionalDoubleSupplier inner;
	private final DoubleSupplier source;
	public FallbackDoubleSupplier(OptionalDoubleSupplier inner, DoubleSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public double getAsDouble() {
		return inner.get().orElseGet(source);
	}
}
