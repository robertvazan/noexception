package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleBinaryOperator implements DoubleBinaryOperator {
	private final OptionalDoubleBinaryOperator inner;
	private final double result;
	@Override public double applyAsDouble(double left, double right) {
		return inner.apply(left, right).orElse(result);
	}
}
