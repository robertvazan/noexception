// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultBooleanSupplier implements BooleanSupplier {
	private final OptionalBooleanSupplier inner;
	private final boolean result;
	public DefaultBooleanSupplier(OptionalBooleanSupplier inner, boolean result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public boolean getAsBoolean() {
		return inner.get().orElse(result);
	}
}
