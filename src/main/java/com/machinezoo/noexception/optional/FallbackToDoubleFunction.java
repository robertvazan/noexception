// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToDoubleFunction<T> implements ToDoubleFunction<T> {
	private final OptionalToDoubleFunction<T> inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(T t) {
		return inner.apply(t).orElseGet(source);
	}
}
