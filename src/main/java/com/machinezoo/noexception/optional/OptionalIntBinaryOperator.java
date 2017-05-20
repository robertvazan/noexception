package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalIntBinaryOperator {
	OptionalInt apply(int left, int right);
	default IntBinaryOperator orElse(int fallback) {
		return new DefaultIntBinaryOperator(this, fallback);
	}
	default IntBinaryOperator orElseGet(IntSupplier source) {
		return new FallbackIntBinaryOperator(this, source);
	}
}
