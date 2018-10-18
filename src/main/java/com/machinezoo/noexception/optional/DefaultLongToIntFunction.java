// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception.optional;

import java.util.function.*;

final class DefaultLongToIntFunction implements LongToIntFunction {
	private final OptionalLongToIntFunction inner;
	private final int result;
	public DefaultLongToIntFunction(OptionalLongToIntFunction inner, int result) {
		this.inner = inner;
		this.result = result;
	}
	@Override public int applyAsInt(long value) {
		return inner.apply(value).orElse(result);
	}
}
