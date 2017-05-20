// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultBiPredicate<T, U> implements BiPredicate<T, U> {
	private final OptionalBiPredicate<T, U> inner;
	private final boolean result;
	@Override public boolean test(T t, U u) {
		return inner.test(t, u).orElse(result);
	}
}
