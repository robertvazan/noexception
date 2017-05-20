// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongPredicate implements LongPredicate {
	private final OptionalLongPredicate inner;
	private final BooleanSupplier source;
	@Override public boolean test(long value) {
		return inner.test(value).orElseGet(source);
	}
}
