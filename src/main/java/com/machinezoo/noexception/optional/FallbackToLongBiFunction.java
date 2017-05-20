// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
	private final OptionalToLongBiFunction<T, U> inner;
	private final LongSupplier source;
	@Override public long applyAsLong(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
