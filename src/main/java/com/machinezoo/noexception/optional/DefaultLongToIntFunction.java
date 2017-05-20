package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongToIntFunction implements LongToIntFunction {
	private final OptionalLongToIntFunction inner;
	private final int defaultValue;
	@Override public int applyAsInt(long value) {
		return inner.apply(value).orElse(defaultValue);
	}
}
