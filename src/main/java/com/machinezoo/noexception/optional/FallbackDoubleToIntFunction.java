package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoubleToIntFunction implements DoubleToIntFunction {
	private final OptionalDoubleToIntFunction inner;
	private final IntSupplier source;
	@Override public int applyAsInt(double value) {
		return inner.apply(value).orElseGet(source);
	}
}
