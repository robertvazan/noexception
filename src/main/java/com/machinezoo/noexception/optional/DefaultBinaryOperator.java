package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultBinaryOperator<T> implements BinaryOperator<T> {
	private final OptionalBinaryOperator<T> inner;
	private final T defaultValue;
	@Override public T apply(T left, T right) {
		return inner.apply(left, right).orElse(defaultValue);
	}
}
