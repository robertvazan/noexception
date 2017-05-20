package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongBinaryOperator implements LongBinaryOperator {
	private final OptionalLongBinaryOperator inner;
	private final LongSupplier source;
	@Override public long applyAsLong(long left, long right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
