// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToDoubleFunction<T> implements ToDoubleFunction<T> {
	private final OptionalToDoubleFunction<T> inner;
	private final double result;
	@Override public double applyAsDouble(T value) {
		return inner.apply(value).orElse(result);
	}
}
