package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleSupplier implements DoubleSupplier {
	private final OptionalDoubleSupplier inner;
	private final double result;
	@Override public double getAsDouble() {
		return inner.get().orElse(result);
	}
}
