// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class DefaultToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
	private final OptionalToDoubleBiFunction<T, U> inner;
	private final double result;
	@Override public double applyAsDouble(T t, U u) {
		return inner.apply(t, u).orElse(result);
	}
}
