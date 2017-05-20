// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalBooleanSupplier extends Supplier<OptionalBoolean> {
	@Override OptionalBoolean get();
	default BooleanSupplier orElse(boolean result) {
		return new DefaultBooleanSupplier(this, result);
	}
	default BooleanSupplier orElseGet(BooleanSupplier source) {
		return new FallbackBooleanSupplier(this, source);
	}
}
