// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleFunction<R> extends DoubleFunction<Optional<R>> {
	@Override Optional<R> apply(double value);
	default DoubleFunction<R> orElse(R result) {
		return new DefaultDoubleFunction<>(this, result);
	}
	default DoubleFunction<R> orElseGet(Supplier<R> source) {
		return new FallbackDoubleFunction<>(this, source);
	}
}
