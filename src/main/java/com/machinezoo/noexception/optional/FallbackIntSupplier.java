package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntSupplier implements IntSupplier {
	private final OptionalIntSupplier inner;
	private final IntSupplier source;
	@Override public int getAsInt() {
		return inner.get().orElseGet(source);
	}
}
