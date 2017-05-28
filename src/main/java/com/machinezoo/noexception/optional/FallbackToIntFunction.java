// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToIntFunction<T> implements ToIntFunction<T> {
	private final OptionalToIntFunction<T> inner;
	private final IntSupplier source;
	@Override public int applyAsInt(T value) {
		return inner.apply(value).orElseGet(source);
	}
}
