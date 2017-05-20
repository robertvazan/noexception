package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongFunction<R> implements LongFunction<R> {
	private final OptionalLongFunction<R> inner;
	private final R defaultValue;
	@Override public R apply(long value) {
		return inner.apply(value).orElse(defaultValue);
	}
}
