package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntUnaryOperator extends IntFunction<OptionalInt> {
	@Override OptionalInt apply(int operand);
	default IntUnaryOperator orElse(int result) {
		return new DefaultIntUnaryOperator(this, result);
	}
	default IntUnaryOperator orElseGet(IntSupplier source) {
		return new FallbackIntUnaryOperator(this, source);
	}
}
