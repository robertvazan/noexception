// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongToDoubleFunction implements LongToDoubleFunction {
	private final OptionalLongToDoubleFunction inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(long value) {
		return inner.apply(value).orElseGet(source);
	}
}
