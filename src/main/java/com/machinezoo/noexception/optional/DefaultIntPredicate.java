// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntPredicate implements IntPredicate {
	private final OptionalIntPredicate inner;
	private final boolean result;
	@Override public boolean test(int value) {
		return inner.test(value).orElse(result);
	}
}
