package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToDoubleFunction<T> implements ToDoubleFunction<T> {
	private final OptionalToDoubleFunction<T> inner;
	private final double defaultValue;
	@Override public double applyAsDouble(T t) {
		return inner.apply(t).orElse(defaultValue);
	}
}
