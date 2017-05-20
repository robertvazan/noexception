package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntToLongFunction implements IntToLongFunction {
	private final OptionalIntToLongFunction inner;
	private final LongSupplier source;
	@Override public long applyAsLong(int value) {
		return inner.apply(value).orElseGet(source);
	}
}
