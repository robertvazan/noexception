// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultIntToDoubleFunction implements IntToDoubleFunction {
	private final OptionalIntToDoubleFunction inner;
	private final double result;
	@Override public double applyAsDouble(int value) {
		return inner.apply(value).orElse(result);
	}
}
