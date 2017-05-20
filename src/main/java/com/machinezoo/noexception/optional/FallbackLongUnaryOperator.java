package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongUnaryOperator implements LongUnaryOperator {
	private final OptionalLongUnaryOperator inner;
	private final LongSupplier source;
	@Override public long applyAsLong(long operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
