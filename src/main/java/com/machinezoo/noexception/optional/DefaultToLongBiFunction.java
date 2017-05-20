// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToLongBiFunction<T, U> implements ToLongBiFunction<T, U> {
	private final OptionalToLongBiFunction<T, U> inner;
	private final long result;
	@Override public long applyAsLong(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
