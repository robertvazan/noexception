package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleToIntFunction implements DoubleToIntFunction {
	private final OptionalDoubleToIntFunction inner;
	private final int defaultValue;
	@Override public int applyAsInt(double value) {
		return inner.apply(value).orElse(defaultValue);
	}
}
