package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntBinaryOperator implements IntBinaryOperator {
	private final OptionalIntBinaryOperator inner;
	private final int defaultValue;
	@Override public int applyAsInt(int left, int right) {
		return inner.apply(left, right).orElse(defaultValue);
	}
}
