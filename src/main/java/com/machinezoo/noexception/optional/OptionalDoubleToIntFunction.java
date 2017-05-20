package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleToIntFunction extends DoubleFunction<OptionalInt> {
	@Override OptionalInt apply(double value);
	default DoubleToIntFunction orElse(int fallback) {
		return new DefaultDoubleToIntFunction(this, fallback);
	}
	default DoubleToIntFunction orElseGet(IntSupplier source) {
		return new FallbackDoubleToIntFunction(this, source);
	}
}
