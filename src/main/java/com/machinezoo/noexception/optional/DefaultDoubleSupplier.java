// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultDoubleSupplier implements DoubleSupplier {
	private final OptionalDoubleSupplier inner;
	private final double result;
	public DefaultDoubleSupplier(OptionalDoubleSupplier inner, double result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public double getAsDouble() {
		return inner.get().orElse(result);
	}
}
