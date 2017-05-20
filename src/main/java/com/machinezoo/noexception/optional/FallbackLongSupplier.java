package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackLongSupplier implements LongSupplier {
	private final OptionalLongSupplier inner;
	private final LongSupplier source;
	@Override public long getAsLong() {
		return inner.get().orElseGet(source);
	}
}
