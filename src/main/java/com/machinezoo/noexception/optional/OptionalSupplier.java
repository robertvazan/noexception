// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalSupplier<T> extends Supplier<Optional<T>> {
	@Override Optional<T> get();
	default Supplier<T> orElse(T result) {
		return new DefaultSupplier<>(this, result);
	}
	default Supplier<T> orElseGet(Supplier<T> source) {
		return new FallbackSupplier<>(this, source);
	}
}
