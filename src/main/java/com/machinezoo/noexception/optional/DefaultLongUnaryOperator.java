// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongUnaryOperator implements LongUnaryOperator {
	private final OptionalLongUnaryOperator inner;
	private final long result;
	@Override public long applyAsLong(long operand) {
		return inner.apply(operand).orElse(result);
	}
}
