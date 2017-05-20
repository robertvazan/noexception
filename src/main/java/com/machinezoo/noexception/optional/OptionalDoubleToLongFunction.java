package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleToLongFunction extends DoubleFunction<OptionalLong> {
	@Override OptionalLong apply(double value);
	default DoubleToLongFunction orElse(long result) {
		return new DefaultDoubleToLongFunction(this, result);
	}
	default DoubleToLongFunction orElseGet(LongSupplier source) {
		return new FallbackDoubleToLongFunction(this, source);
	}
}
