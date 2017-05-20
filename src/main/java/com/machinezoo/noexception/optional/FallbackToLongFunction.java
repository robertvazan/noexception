package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToLongFunction<T> implements ToLongFunction<T> {
	private final OptionalToLongFunction<T> inner;
	private final LongSupplier source;
	@Override public long applyAsLong(T t) {
		return inner.apply(t).orElseGet(source);
	}
}
