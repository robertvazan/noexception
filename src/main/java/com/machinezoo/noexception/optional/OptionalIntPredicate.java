package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalIntPredicate {
	OptionalBoolean test(int value);
	default IntPredicate orElse(boolean result) {
		return new DefaultIntPredicate(this, result);
	}
	default IntPredicate orElseGet(BooleanSupplier source) {
		return new FallbackIntPredicate(this, source);
	}
}
