package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToDoubleFunction<T> extends Function<T, OptionalDouble> {
	@Override OptionalDouble apply(T t);
	default ToDoubleFunction<T> orElse(double fallback) {
		return new DefaultToDoubleFunction<T>(this, fallback);
	}
	default ToDoubleFunction<T> orElseGet(DoubleSupplier source) {
		return new FallbackToDoubleFunction<T>(this, source);
	}
}
