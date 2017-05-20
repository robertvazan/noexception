// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultLongToIntFunction implements LongToIntFunction {
	private final OptionalLongToIntFunction inner;
	private final int result;
	@Override public int applyAsInt(long value) {
		return inner.apply(value).orElse(result);
	}
}
