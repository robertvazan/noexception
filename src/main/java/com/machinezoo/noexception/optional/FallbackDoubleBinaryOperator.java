// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoubleBinaryOperator implements DoubleBinaryOperator {
	private final OptionalDoubleBinaryOperator inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(double left, double right) {
		return inner.apply(left, right).orElseGet(source);
	}
}
