package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToDoubleBiFunction<T, U> extends BiFunction<T, U, OptionalDouble> {
	@Override OptionalDouble apply(T t, U u);
	default ToDoubleBiFunction<T, U> orElse(double fallback) {
		return new DefaultToDoubleBiFunction<T, U>(this, fallback);
	}
	default ToDoubleBiFunction<T, U> orElseGet(DoubleSupplier source) {
		return new FallbackToDoubleBiFunction<T, U>(this, source);
	}
}
