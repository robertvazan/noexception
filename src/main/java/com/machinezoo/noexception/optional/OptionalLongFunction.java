package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongFunction<R> extends LongFunction<Optional<R>> {
	@Override Optional<R> apply(long value);
	default LongFunction<R> orElse(R fallback) {
		return new DefaultLongFunction<>(this, fallback);
	}
	default LongFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackLongFunction<>(this, source);
	}
}
