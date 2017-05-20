// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongSupplier extends Supplier<OptionalLong> {
	@Override OptionalLong get();
	default LongSupplier orElse(long result) {
		return new DefaultLongSupplier(this, result);
	}
	default LongSupplier orElseGet(LongSupplier source) {
		return new FallbackLongSupplier(this, source);
	}
}
