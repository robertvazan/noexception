package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntPredicate implements IntPredicate {
	private final OptionalIntPredicate inner;
	private final BooleanSupplier source;
	@Override public boolean test(int value) {
		return inner.test(value).orElseGet(source);
	}
}
