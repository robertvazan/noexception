package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackSupplier<T> implements Supplier<T> {
	private final OptionalSupplier<T> supplier;
	private final Supplier<T> source;
	@Override public T get() {
		return supplier.get().orElseGet(source);
	}
}
