package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntToLongFunction implements IntToLongFunction {
	private final OptionalIntToLongFunction inner;
	private final long defaultValue;
	@Override public long applyAsLong(int value) {
		return inner.apply(value).orElse(defaultValue);
	}
}
