package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToLongFunction<T> implements ToLongFunction<T> {
	private final OptionalToLongFunction<T> inner;
	private final long defaultValue;
	@Override public long applyAsLong(T t) {
		return inner.apply(t).orElse(defaultValue);
	}
}
