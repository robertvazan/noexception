// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackDoubleSupplier implements DoubleSupplier {
	private final OptionalDoubleSupplier inner;
	private final DoubleSupplier source;
	@Override public double getAsDouble() {
		return inner.get().orElseGet(source);
	}
}
