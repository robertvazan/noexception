// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalPredicate<T> {
	OptionalBoolean test(T t);
	default Predicate<T> orElse(boolean result) {
		return new DefaultPredicate<T>(this, result);
	}
	default Predicate<T> orElseGet(BooleanSupplier source) {
		return new FallbackPredicate<T>(this, source);
	}
}
