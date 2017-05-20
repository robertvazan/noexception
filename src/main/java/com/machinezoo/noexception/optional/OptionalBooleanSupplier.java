package com.machinezoo.noexception.optional;

import java.util.function.*;

@FunctionalInterface public interface OptionalBooleanSupplier extends Supplier<OptionalBoolean> {
	@Override OptionalBoolean get();
	default BooleanSupplier orElse(boolean fallback) {
		return new DefaultBooleanSupplier(this, fallback);
	}
	default BooleanSupplier orElseGet(BooleanSupplier source) {
		return new FallbackBooleanSupplier(this, source);
	}
}
