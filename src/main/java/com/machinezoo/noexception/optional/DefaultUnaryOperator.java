package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultUnaryOperator<T> implements UnaryOperator<T> {
	private final OptionalUnaryOperator<T> inner;
	private final T result;
	@Override public T apply(T operand) {
		return inner.apply(operand).orElse(result);
	}
}
