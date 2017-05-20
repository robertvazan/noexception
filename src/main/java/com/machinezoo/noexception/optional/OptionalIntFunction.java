package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntFunction<R> extends IntFunction<Optional<R>> {
	@Override Optional<R> apply(int value);
	default IntFunction<R> orElse(R result) {
		return new DefaultIntFunction<>(this, result);
	}
	default IntFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackIntFunction<>(this, source);
	}
}
