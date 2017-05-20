package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackBinaryOperator<T> implements BinaryOperator<T> {
	private final OptionalBinaryOperator<T> inner;
	private final Supplier<T> source;
	@Override public T apply(T left, T right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
