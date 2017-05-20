package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackBiPredicate<T, U> implements BiPredicate<T, U> {
	private final OptionalBiPredicate<T, U> inner;
	private final BooleanSupplier source;
	@Override public boolean test(T t, U u) {
		return inner.test(t, u).orElseGet(source);
	}
}
