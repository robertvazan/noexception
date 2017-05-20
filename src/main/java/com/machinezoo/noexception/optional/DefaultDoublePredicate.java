package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoublePredicate implements DoublePredicate {
	private final OptionalDoublePredicate inner;
	private final boolean result;
	@Override public boolean test(double value) {
		return inner.test(value).orElse(result);
	}
}
