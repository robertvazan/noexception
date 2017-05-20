// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultBiFunction<T, U, R> implements BiFunction<T, U, R> {
	private final OptionalBiFunction<T, U, R> inner;
	private final R result;
	@Override public R apply(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
