package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntFunction<R> implements IntFunction<R> {
	private final OptionalIntFunction<R> inner;
	private final R result;
	@Override public R apply(int value) {
		return inner.apply(value).orElse(result);
	}
}
