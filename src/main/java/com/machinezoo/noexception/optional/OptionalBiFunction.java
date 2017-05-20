package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalBiFunction<T, U, R> extends BiFunction<T, U, Optional<R>> {
	@Override Optional<R> apply(T t, U u);
	default BiFunction<T, U, R> orElse(R result) {
		return new DefaultBiFunction<>(this, result);
	}
	default BiFunction<T, U, R> orElseGet(Supplier<R> source) {
		return new FallbackBiFunction<>(this, source);
	}
}
