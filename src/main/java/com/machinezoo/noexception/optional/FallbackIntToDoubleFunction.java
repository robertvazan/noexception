package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntToDoubleFunction implements IntToDoubleFunction {
	private final OptionalIntToDoubleFunction inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(int value) {
		return inner.apply(value).orElseGet(source);
	}
}
