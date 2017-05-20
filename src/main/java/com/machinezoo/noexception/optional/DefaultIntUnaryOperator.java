// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntUnaryOperator implements IntUnaryOperator {
	private final OptionalIntUnaryOperator inner;
	private final int result;
	@Override public int applyAsInt(int operand) {
		return inner.apply(operand).orElse(result);
	}
}
