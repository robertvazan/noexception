package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackFunction<T, R> implements Function<T, R> {
	private final OptionalFunction<T, R> inner;
	private final Supplier<R> source;
	@Override public R apply(T t) {
		return inner.apply(t).orElseGet(source);
	}
}
