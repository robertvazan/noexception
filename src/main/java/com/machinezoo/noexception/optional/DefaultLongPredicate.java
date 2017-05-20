package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongPredicate implements LongPredicate {
	private final OptionalLongPredicate inner;
	private final boolean result;
	@Override public boolean test(long value) {
		return inner.test(value).orElse(result);
	}
}
