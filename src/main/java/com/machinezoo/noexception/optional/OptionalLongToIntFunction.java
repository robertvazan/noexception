package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongToIntFunction extends LongFunction<OptionalInt> {
	@Override OptionalInt apply(long value);
	default LongToIntFunction orElse(int result) {
		return new DefaultLongToIntFunction(this, result);
	}
	default LongToIntFunction orElseGet(IntSupplier source) {
		return new FallbackLongToIntFunction(this, source);
	}
}
