package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalFunction<T, R> extends Function<T, Optional<R>> {
	@Override Optional<R> apply(T t);
	default Function<T, R> orElse(R result) {
		return new DefaultFunction<>(this, result);
	}
	default Function<T, R> orElseGet(Supplier<R> source) {
		return new FallbackFunction<>(this, source);
	}
}
