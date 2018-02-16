// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultIntSupplier implements IntSupplier {
	private final OptionalIntSupplier inner;
	private final int result;
	public DefaultIntSupplier(OptionalIntSupplier inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int getAsInt() {
		return inner.get().orElse(result);
	}
}
