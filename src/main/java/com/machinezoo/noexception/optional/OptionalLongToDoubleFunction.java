// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongToDoubleFunction extends LongFunction<OptionalDouble> {
	@Override OptionalDouble apply(long value);
	default LongToDoubleFunction orElse(double result) {
		return new DefaultLongToDoubleFunction(this, result);
	}
	default LongToDoubleFunction orElseGet(DoubleSupplier source) {
		return new FallbackLongToDoubleFunction(this, source);
	}
}
