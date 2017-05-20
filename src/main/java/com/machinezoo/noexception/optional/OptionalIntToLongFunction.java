package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntToLongFunction extends IntFunction<OptionalLong> {
	@Override OptionalLong apply(int value);
	default IntToLongFunction orElse(long fallback) {
		return new DefaultIntToLongFunction(this, fallback);
	}
	default IntToLongFunction orElseGet(LongSupplier source) {
		return new FallbackIntToLongFunction(this, source);
	}
}
