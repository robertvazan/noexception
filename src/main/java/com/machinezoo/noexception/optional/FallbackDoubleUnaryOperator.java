// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoubleUnaryOperator implements DoubleUnaryOperator {
	private final OptionalDoubleUnaryOperator inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(double operand) {
		return inner.apply(operand).orElseGet(source);
	}
}
