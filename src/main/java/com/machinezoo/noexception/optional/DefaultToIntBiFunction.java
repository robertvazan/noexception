// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToIntBiFunction<T, U> implements ToIntBiFunction<T, U> {
	private final OptionalToIntBiFunction<T, U> inner;
	private final int result;
	@Override public int applyAsInt(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
