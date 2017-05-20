// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalBiPredicate<T, U> {
	OptionalBoolean test(T t, U u);
	default BiPredicate<T, U> orElse(boolean result) {
		return new DefaultBiPredicate<T, U>(this, result);
	}
	default BiPredicate<T, U> orElseGet(BooleanSupplier source) {
		return new FallbackBiPredicate<T, U>(this, source);
	}
}
