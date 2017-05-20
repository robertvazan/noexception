package com.machinezoo.noexception.optional;

import java.util.function.*;
import lombok.*;

@RequiredArgsConstructor final class FallbackToDoubleBiFunction<T, U> implements ToDoubleBiFunction<T, U> {
	private final OptionalToDoubleBiFunction<T, U> inner;
	private final DoubleSupplier source;
	@Override public double applyAsDouble(T t, U u) {
		return inner.apply(t, u).orElseGet(source);
	}
}
