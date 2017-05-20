package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleSupplier extends Supplier<OptionalDouble> {
	@Override OptionalDouble get();
	default DoubleSupplier orElse(double fallback) {
		return new DefaultDoubleSupplier(this, fallback);
	}
	default DoubleSupplier orElseGet(DoubleSupplier source) {
		return new FallbackDoubleSupplier(this, source);
	}
}
