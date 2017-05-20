package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToIntFunction<T> implements ToIntFunction<T> {
	private final OptionalToIntFunction<T> inner;
	private final int result;
	@Override public int applyAsInt(T t) {
		return inner.apply(t).orElse(result);
	}
}
