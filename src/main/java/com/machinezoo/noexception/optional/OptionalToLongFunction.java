package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToLongFunction<T> extends Function<T, OptionalLong> {
	@Override OptionalLong apply(T t);
	default ToLongFunction<T> orElse(long fallback) {
		return new DefaultToLongFunction<T>(this, fallback);
	}
	default ToLongFunction<T> orElseGet(LongSupplier source) {
		return new FallbackToLongFunction<T>(this, source);
	}
}
