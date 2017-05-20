package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongSupplier extends Supplier<OptionalLong> {
	@Override OptionalLong get();
	default LongSupplier orElse(long fallback) {
		return new DefaultLongSupplier(this, fallback);
	}
	default LongSupplier orElseGet(LongSupplier source) {
		return new FallbackLongSupplier(this, source);
	}
}
