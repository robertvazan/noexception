package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongSupplier implements LongSupplier {
	private final OptionalLongSupplier inner;
	private final long value;
	@Override public long getAsLong() {
		return inner.get().orElse(value);
	}
}
