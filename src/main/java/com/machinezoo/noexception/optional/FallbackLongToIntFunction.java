package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongToIntFunction implements LongToIntFunction {
	private final OptionalLongToIntFunction inner;
	private final IntSupplier source;
	@Override public int applyAsInt(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
