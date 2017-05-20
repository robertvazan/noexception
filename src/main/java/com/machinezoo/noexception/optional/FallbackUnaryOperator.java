package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackUnaryOperator<T> implements UnaryOperator<T> {
	private final OptionalUnaryOperator<T> inner;
	private final Supplier<T> source;
	@Override public T apply(T operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
