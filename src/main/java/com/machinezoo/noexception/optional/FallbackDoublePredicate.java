// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoublePredicate implements DoublePredicate {
	private final OptionalDoublePredicate inner;
	private final BooleanSupplier source;
	@Override public boolean test(double value) {
		return inner.test(value).orElseGet(source);
	}
}
