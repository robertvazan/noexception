package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalLongBinaryOperator {
	OptionalLong apply(long left, long right);
	default LongBinaryOperator orElse(long result) {
		return new DefaultLongBinaryOperator(this, result);
	}
	default LongBinaryOperator orElseGet(LongSupplier source) {
		return new FallbackLongBinaryOperator(this, source);
	}
}
