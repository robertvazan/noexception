package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongToIntFunction extends LongFunction<OptionalInt> {
	@Override OptionalInt apply(long value);
	default LongToIntFunction orElse(int fallback) {
		return new DefaultLongToIntFunction(this, fallback);
	}
	default LongToIntFunction orElseGet(IntSupplier source) {
		return new FallbackLongToIntFunction(this, source);
	}
}
