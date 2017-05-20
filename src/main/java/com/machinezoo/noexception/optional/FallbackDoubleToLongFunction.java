// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoubleToLongFunction implements DoubleToLongFunction {
	private final OptionalDoubleToLongFunction inner;
	private final LongSupplier source;
	@Override public long applyAsLong(double value) {
		return inner.apply(value).orElseGet(source);
	}
}
