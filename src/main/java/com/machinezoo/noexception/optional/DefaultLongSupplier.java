package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongSupplier implements LongSupplier {
	private final OptionalLongSupplier inner;
	private final long result;
	@Override public long getAsLong() {
		return inner.get().orElse(result);
	}
}
