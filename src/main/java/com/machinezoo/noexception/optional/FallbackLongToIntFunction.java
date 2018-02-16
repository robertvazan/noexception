// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class FallbackLongToIntFunction implements LongToIntFunction {
	private final OptionalLongToIntFunction inner;
	private final IntSupplier source;
	public FallbackLongToIntFunction(OptionalLongToIntFunction inner, IntSupplier source) {
		this.inner = inner;
		this.source = source;
	}
	@Override public int applyAsInt(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
