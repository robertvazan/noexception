// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalUnaryOperator<T> extends Function<T, Optional<T>> {
	@Override Optional<T> apply(T operand);
	default UnaryOperator<T> orElse(T result) {
		return new DefaultUnaryOperator<>(this, result);
	}
	default UnaryOperator<T> orElseGet(Supplier<T> source) {
		return new FallbackUnaryOperator<>(this, source);
	}
}
