package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongToDoubleFunction extends LongFunction<OptionalDouble> {
	@Override OptionalDouble apply(long value);
	default LongToDoubleFunction orElse(double fallback) {
		return new DefaultLongToDoubleFunction(this, fallback);
	}
	default LongToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackLongToDoubleFunction(this, source);
	}
}
