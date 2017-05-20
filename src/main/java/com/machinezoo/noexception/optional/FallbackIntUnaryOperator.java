package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntUnaryOperator implements IntUnaryOperator {
	private final OptionalIntUnaryOperator inner;
	private final IntSupplier source;
	@Override public int applyAsInt(int operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
