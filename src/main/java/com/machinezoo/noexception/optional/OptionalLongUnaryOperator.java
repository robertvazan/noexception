package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongUnaryOperator extends LongFunction<OptionalLong> {
	@Override OptionalLong apply(long operand);
	default LongUnaryOperator orElse(long fallback) {
		return new DefaultLongUnaryOperator(this, fallback);
	}
	default LongUnaryOperator orElseGet(LongSupplier source) {
		return new FallbackLongUnaryOperator(this, source);
	}
}
