package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntToDoubleFunction extends IntFunction<OptionalDouble> {
	@Override OptionalDouble apply(int value);
	default IntToDoubleFunction orElse(double fallback) {
		return new DefaultIntToDoubleFunction(this, fallback);
	}
	default IntToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackIntToDoubleFunction(this, source);
	}
}
