package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackSupplier<T> implements Supplier<T> {
	private final OptionalSupplier<T> inner;
	private final Supplier<T> source;
	@Override public T get() {
		return inner.get().orElseGet(source);
	}
}
