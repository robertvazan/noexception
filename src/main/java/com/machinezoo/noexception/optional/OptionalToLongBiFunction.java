// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToLongBiFunction<T, U> extends BiFunction<T, U, OptionalLong> {
	@Override OptionalLong apply(T t, U u);
	default ToLongBiFunction<T, U> orElse(long result) {
		return new DefaultToLongBiFunction<T, U>(this, result);
	}
	default ToLongBiFunction<T, U> orElseGet(LongSupplier source) {
		return new FallbackToLongBiFunction<T, U>(this, source);
	}
}
