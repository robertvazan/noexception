// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalToLongFunction<T> extends Function<T, OptionalLong> {
	@Override OptionalLong apply(T t);
	default ToLongFunction<T> orElse(long result) {
		return new DefaultToLongFunction<T>(this, result);
	}
	default ToLongFunction<T> orElseGet(LongSupplier source) {
		return new FallbackToLongFunction<T>(this, source);
	}
}
