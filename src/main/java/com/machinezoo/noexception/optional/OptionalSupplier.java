package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalSupplier<T> extends Supplier<Optional<T>> {
	@Override Optional<T> get();
	default Supplier<T> orElse(T fallback) {
		return new DefaultSupplier<>(this, fallback);
	}
	default Supplier<T> orElseGet(Supplier<T> source) {
		return new FallbackSupplier<>(this, source);
	}
}
