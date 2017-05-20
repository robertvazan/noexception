package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultBooleanSupplier implements BooleanSupplier {
	private final OptionalBooleanSupplier inner;
	private final boolean value;
	@Override public boolean getAsBoolean() {
		return inner.get().orElse(value);
	}
}
