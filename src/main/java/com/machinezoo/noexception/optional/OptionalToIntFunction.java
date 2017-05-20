// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToIntFunction<T> extends Function<T, OptionalInt> {
	@Override OptionalInt apply(T t);
	default ToIntFunction<T> orElse(int result) {
		return new DefaultToIntFunction<T>(this, result);
	}
	default ToIntFunction<T> orElseGet(IntSupplier source) {
		return new FallbackToIntFunction<T>(this, source);
	}
}
