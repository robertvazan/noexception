// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultDoubleFunction<R> implements DoubleFunction<R> {
	private final OptionalDoubleFunction<R> inner;
	private final R result;
	@Override public R apply(double value) {
		return inner.apply(value).orElse(result);
	}
}
