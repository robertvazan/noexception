package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalDoublePredicate {
	OptionalBoolean test(double value);
	default DoublePredicate orElse(boolean result) {
		return new DefaultDoublePredicate(this, result);
	}
	default DoublePredicate orElseGet(BooleanSupplier source) {
		return new FallbackDoublePredicate(this, source);
	}
}
