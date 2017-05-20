package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleToLongFunction implements DoubleToLongFunction {
	private final OptionalDoubleToLongFunction inner;
	private final long defaultValue;
	@Override public long applyAsLong(double value) {
		return inner.apply(value).orElse(defaultValue);
	}
}
