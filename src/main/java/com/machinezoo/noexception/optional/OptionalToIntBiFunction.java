package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToIntBiFunction<T, U> extends BiFunction<T, U, OptionalInt> {
	@Override OptionalInt apply(T t, U u);
	default ToIntBiFunction<T, U> orElse(int result) {
		return new DefaultToIntBiFunction<T, U>(this, result);
	}
	default ToIntBiFunction<T, U> orElseGet(IntSupplier source) {
		return new FallbackToIntBiFunction<T, U>(this, source);
	}
}
