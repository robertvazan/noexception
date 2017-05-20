package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongBinaryOperator implements LongBinaryOperator {
	private final OptionalLongBinaryOperator inner;
	private final long result;
	@Override public long applyAsLong(long left, long right) {
		return inner.apply(left, right).orElse(result);
	}
}
