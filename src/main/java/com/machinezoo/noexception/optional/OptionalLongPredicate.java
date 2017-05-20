package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalLongPredicate {
	OptionalBoolean test(long value);
	default LongPredicate orElse(boolean result) {
		return new DefaultLongPredicate(this, result);
	}
	default LongPredicate orElseGet(BooleanSupplier source) {
		return new FallbackLongPredicate(this, source);
	}
}
