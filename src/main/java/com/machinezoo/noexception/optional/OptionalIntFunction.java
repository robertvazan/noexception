package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntFunction<R> extends IntFunction<Optional<R>> {
	@Override Optional<R> apply(int value);
	default IntFunction<R> orElse(R fallback) {
		return new DefaultIntFunction<>(this, fallback);
	}
	default IntFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackIntFunction<>(this, source);
	}
}
