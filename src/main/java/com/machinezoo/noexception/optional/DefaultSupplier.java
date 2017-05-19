package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultSupplier<T> implements Supplier<T> {
	private final OptionalSupplier<T> supplier;
	private final T value;
	@Override public T get() {
		return supplier.get().orElse(value);
	}
}
