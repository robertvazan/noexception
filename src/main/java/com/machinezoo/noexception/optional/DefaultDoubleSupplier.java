package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleSupplier implements DoubleSupplier {
	private final OptionalDoubleSupplier inner;
	private final double value;
	@Override public double getAsDouble() {
		return inner.get().orElse(value);
	}
}
