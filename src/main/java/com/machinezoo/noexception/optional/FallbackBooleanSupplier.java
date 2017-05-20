// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackBooleanSupplier implements BooleanSupplier {
	private final OptionalBooleanSupplier inner;
	private final BooleanSupplier source;
	@Override public boolean getAsBoolean() {
		return inner.get().orElseGet(source);
	}
}
