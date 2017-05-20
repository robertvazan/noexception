package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntBinaryOperator implements IntBinaryOperator {
	private final OptionalIntBinaryOperator inner;
	private final IntSupplier source;
	@Override public int applyAsInt(int left, int right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
