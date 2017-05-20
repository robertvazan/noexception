package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultFunction<T, R> implements Function<T, R> {
	private final OptionalFunction<T, R> inner;
	private final R defaultValue;
	@Override public R apply(T t) {
		return inner.apply(t).orElse(defaultValue);
	}
}
