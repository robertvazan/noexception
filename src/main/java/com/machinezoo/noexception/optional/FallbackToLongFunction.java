// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToLongFunction<T> implements ToLongFunction<T> {
	private final OptionalToLongFunction<T> inner;
	private final LongSupplier source;
	@Override public long applyAsLong(T value) {
		return inner.apply(value).orElseGet(source);
	}
}
