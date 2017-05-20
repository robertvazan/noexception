package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongFunction<R> implements LongFunction<R> {
	private final OptionalLongFunction<R> inner;
	private final Supplier<R> source;
	@Override public R apply(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
