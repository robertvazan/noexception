// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception.optional;

import java.util.*;
import java.util.function.*;

@FunctionalInterface public interface OptionalDoubleBinaryOperator {
	OptionalDouble apply(double left, double right);
	default DoubleBinaryOperator orElse(double result) {
		return new DefaultDoubleBinaryOperator(this, result);
	}
	default DoubleBinaryOperator orElseGet(DoubleSupplier source) {
		return new FallbackDoubleBinaryOperator(this, source);
	}
}
