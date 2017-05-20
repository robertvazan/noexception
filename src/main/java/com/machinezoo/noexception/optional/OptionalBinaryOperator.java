package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalBinaryOperator<T> extends BiFunction<T, T, Optional<T>> {
	@Override Optional<T> apply(T left, T right);
	default BinaryOperator<T> orElse(T fallback) {
		return new DefaultBinaryOperator<>(this, fallback);
	}
	default BinaryOperator<T> orElseGet(Supplier<T> source) {
		return new FallbackBinaryOperator<>(this, source);
	}
}
