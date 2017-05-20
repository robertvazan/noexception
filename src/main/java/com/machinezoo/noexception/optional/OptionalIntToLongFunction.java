// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntToLongFunction extends IntFunction<OptionalLong> {
	@Override OptionalLong apply(int value);
	default IntToLongFunction orElse(long result) {
		return new DefaultIntToLongFunction(this, result);
	}
	default IntToLongFunction orElseGet(LongSupplier source) {
		return new FallbackIntToLongFunction(this, source);
	}
}
