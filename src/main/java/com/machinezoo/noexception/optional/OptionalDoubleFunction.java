package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleFunction<R> extends DoubleFunction<Optional<R>> {
	@Override Optional<R> apply(double value);
	default DoubleFunction<R> orElse(R fallback) {
		return new DefaultDoubleFunction<>(this, fallback);
	}
	default DoubleFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackDoubleFunction<>(this, source);
	}
}
