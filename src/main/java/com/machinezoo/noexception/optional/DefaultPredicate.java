package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultPredicate<T> implements Predicate<T> {
	private final OptionalPredicate<T> inner;
	private final boolean result;
	@Override public boolean test(T t) {
		return inner.test(t).orElse(result);
	}
}
