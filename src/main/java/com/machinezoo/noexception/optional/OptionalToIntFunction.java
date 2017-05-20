package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToIntFunction<T> extends Function<T, OptionalInt> {
	@Override OptionalInt apply(T t);
	default ToIntFunction<T> orElse(int fallback) {
		return new DefaultToIntFunction<T>(this, fallback);
	}
	default ToIntFunction<T> orElseGet(IntSupplier source) {
		return new FallbackToIntFunction<T>(this, source);
	}
}
