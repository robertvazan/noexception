// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackIntFunction<R> implements IntFunction<R> {
	private final OptionalIntFunction<R> inner;
	private final Supplier<R> source;
	@Override public R apply(int value) {
		return inner.apply(value).orElseGet(source);
	}
}
