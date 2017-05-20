package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntSupplier extends Supplier<OptionalInt> {
	@Override OptionalInt get();
	default IntSupplier orElse(int result) {
		return new DefaultIntSupplier(this, result);
	}
	default IntSupplier orElseGet(IntSupplier source) {
		return new FallbackIntSupplier(this, source);
	}
}
