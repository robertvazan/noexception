package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleUnaryOperator extends DoubleFunction<OptionalDouble> {
	@Override OptionalDouble apply(double operand);
	default DoubleUnaryOperator orElse(double result) {
		return new DefaultDoubleUnaryOperator(this, result);
	}
	default DoubleUnaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleUnaryOperator(this, source);
	}
}
